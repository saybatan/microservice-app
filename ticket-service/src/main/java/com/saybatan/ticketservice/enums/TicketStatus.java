package com.saybatan.ticketservice.enums;

import lombok.Getter;

@Getter
public enum TicketStatus {

    OPEN("Açık"),
    IN_PROGRESS("İşlemde"),
    RESOLVED("Çözüldü"),
    CLOSED("Kapalı")
    ;

    private final String label;

    TicketStatus(String label) {
        this.label = label;
    }
}
