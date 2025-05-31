package com.saybatan.ticketservice.service.impl;

import com.saybatan.servicecommon.client.AccountServiceClient;
import com.saybatan.servicecommon.client.contract.AccountResponseDto;
import com.saybatan.ticketservice.dto.TicketResponseDto;
import com.saybatan.ticketservice.dto.TicketSaveRequestDto;
import com.saybatan.ticketservice.dto.TicketUpdateRequestDto;
import com.saybatan.ticketservice.entity.Ticket;
import com.saybatan.ticketservice.entity.elasticsearch.TicketModel;
import com.saybatan.ticketservice.enums.PriorityType;
import com.saybatan.ticketservice.enums.TicketStatus;
import com.saybatan.ticketservice.repository.TicketRepository;
import com.saybatan.ticketservice.repository.elasticsearch.TicketElasticRepository;
import com.saybatan.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketElasticRepository ticketElasticRepository;
    private final ModelMapper modelMapper;
    private final AccountServiceClient accountServiceClient;
    private final TicketNotificationServiceImpl ticketNotificationServiceImpl;

    @Override
    @Transactional
    public TicketResponseDto save(TicketSaveRequestDto ticketSaveRequestDto) {
        Ticket ticket = new Ticket();
        if (ticketSaveRequestDto.getDescription() == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        ticket.setDescription(ticketSaveRequestDto.getDescription());
        ticket.setNotes(ticketSaveRequestDto.getNotes());
        ticket.setTicketDate(ticketSaveRequestDto.getTicketDate());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketSaveRequestDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketSaveRequestDto.getPriorityType()));

        ResponseEntity<AccountResponseDto> accountDtoResponseEntity = accountServiceClient.getById(ticketSaveRequestDto.getAssignee());
        ticket.setAssignee(accountDtoResponseEntity.getBody().getId());

        ticket = ticketRepository.save(ticket);

        // TicketNotification mesajını oluştur ve kuyruğa gönder
        ticketNotificationServiceImpl.sendToQueue(ticket);

        TicketModel ticketModel = TicketModel.builder()
                .id(ticket.getId())
                .assignee(accountDtoResponseEntity.getBody().getFullName())
                .description(ticket.getDescription())
                .notes(ticket.getNotes())
                .ticketDate(ticket.getTicketDate())
                .priorityType(ticket.getPriorityType().getLabel())
                .ticketStatus(ticket.getTicketStatus().getLabel())
                .build();

        ticketElasticRepository.save(ticketModel);

        return modelMapper.map(ticket, TicketResponseDto.class);
    }

    @Override
    public TicketResponseDto update(String id, TicketUpdateRequestDto ticketUpdateRequestDto) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        Ticket updatedTicket = optionalTicket.map(ticket -> {
            ticket.setDescription(ticketUpdateRequestDto.getDescription());
            ticket.setNotes(ticketUpdateRequestDto.getNotes());
            ticket.setTicketStatus(TicketStatus.valueOf(ticketUpdateRequestDto.getTicketStatus()));
            ticket.setPriorityType(PriorityType.valueOf(ticketUpdateRequestDto.getPriorityType()));
            return ticket;
        }).orElseThrow(IllegalArgumentException::new);

        updatedTicket = ticketRepository.save(updatedTicket);

        return modelMapper.map(updatedTicket, TicketResponseDto.class);
    }

    @Override
    public TicketResponseDto getById(String id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(ticket, TicketResponseDto.class);
    }

    @Override
    public Page<TicketResponseDto> getPagination(Pageable pageable) {
        Page<Ticket> ticketPage = ticketRepository.findAll(pageable);
        return ticketPage.map(ticket -> modelMapper.map(ticket, TicketResponseDto.class));
    }
}
