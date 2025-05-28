package com.saybatan.servicecommon.messaging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketNotification {

    private String ticketId;
    private String accountId;
    private String ticketDescription;

}
