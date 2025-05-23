package com.saybatan.ticketservice.service;

import com.saybatan.ticketservice.dto.TicketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface TicketService {

    TicketDto save(TicketDto ticketDto);
    TicketDto update(String id, TicketDto ticketDto);
    TicketDto getById(String id);
    Page<TicketDto> getPagination(Pageable pageable);

}
