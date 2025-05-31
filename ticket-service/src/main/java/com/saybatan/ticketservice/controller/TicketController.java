package com.saybatan.ticketservice.controller;


import com.saybatan.ticketservice.dto.TicketResponseDto;
import com.saybatan.ticketservice.dto.TicketSaveRequestDto;
import com.saybatan.ticketservice.dto.TicketUpdateRequestDto;
import com.saybatan.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/ticket")
@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TicketResponseDto> createTicket(@RequestBody TicketSaveRequestDto ticketSaveRequestDto) {
        return ResponseEntity.ok(ticketService.save(ticketSaveRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDto> updateTicket(@PathVariable String id, @RequestBody TicketUpdateRequestDto ticketUpdateRequestDto) {
        return ResponseEntity.ok(ticketService.update(id, ticketUpdateRequestDto));
    }

    @GetMapping
    public ResponseEntity<Page<TicketResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(ticketService.getPagination(pageable));
    }

}
