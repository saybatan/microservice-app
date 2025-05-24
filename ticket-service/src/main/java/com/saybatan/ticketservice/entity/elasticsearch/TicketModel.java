package com.saybatan.ticketservice.entity.elasticsearch;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Document(indexName = "ticket")
public class TicketModel implements Serializable {

    @Id
    private String id;
    private String description;
    private String notes;
    private String assignee;
    private String priorityType;
    private String ticketStatus;
    private Date ticketDate;
}
