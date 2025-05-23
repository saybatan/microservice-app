package com.saybatan.ticketservice.entity;


import com.saybatan.ticketservice.enums.PriorityType;
import com.saybatan.ticketservice.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket extends BaseEntityModel {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "notes", length = 4000)
    private String notes;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "ticket_Date")
    private Date ticketDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "priority_type")
    private PriorityType priorityType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ticket_status")
    private TicketStatus ticketStatus;

}

