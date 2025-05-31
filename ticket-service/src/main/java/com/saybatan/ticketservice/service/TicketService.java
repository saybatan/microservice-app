package com.saybatan.ticketservice.service;

import com.saybatan.ticketservice.dto.TicketResponseDto;
import com.saybatan.ticketservice.dto.TicketSaveRequestDto;
import com.saybatan.ticketservice.dto.TicketUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {

    TicketResponseDto save(TicketSaveRequestDto ticketSaveRequestDto);
    TicketResponseDto update(String id, TicketUpdateRequestDto ticketUpdateRequestDto);
    TicketResponseDto getById(String id);
    Page<TicketResponseDto> getPagination(Pageable pageable);

}
