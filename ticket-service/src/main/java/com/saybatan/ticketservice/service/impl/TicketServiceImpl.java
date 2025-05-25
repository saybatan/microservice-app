package com.saybatan.ticketservice.service.impl;

import com.saybatan.servicecommon.client.AccountServiceClient;
import com.saybatan.servicecommon.client.contract.AccountDto;
import com.saybatan.ticketservice.dto.TicketDto;
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

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketElasticRepository ticketElasticRepository;
    private final ModelMapper modelMapper;
    private final AccountServiceClient accountServiceClient;

    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        if (ticketDto.getDescription() == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        ticket.setDescription(ticketDto.getDescription());
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));

        ResponseEntity<AccountDto> accountDtoResponseEntity = accountServiceClient.getById(ticketDto.getAssignee());
        ticket.setAssignee(accountDtoResponseEntity.getBody().getId());

        ticket = ticketRepository.save(ticket);

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

        ticketDto.setId(ticket.getId());

        return ticketDto;
    }

    @Override
    public TicketDto update(String id, TicketDto ticketDto) {
        return null;
    }

    @Override
    public TicketDto getById(String id) {
        return null;
    }

    @Override
    public Page<TicketDto> getPagination(Pageable pageable) {
        return null;
    }
}
