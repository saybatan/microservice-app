package com.saybatan.ticketservice.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketUpdateRequestDto {

    private String description;
    private String notes;
    private String priorityType;
    private String ticketStatus;
}
