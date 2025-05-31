package com.saybatan.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TicketSaveRequestDto {

    private String description;
    private String notes;
    private String assignee;
    private String priorityType;
    private String ticketStatus;
    private Date ticketDate;
}
