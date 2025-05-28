package com.saybatan.ticketservice.service.impl;

import com.saybatan.servicecommon.messaging.TicketNotification;
import com.saybatan.ticketservice.entity.Ticket;
import com.saybatan.ticketservice.service.TicketNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketNotificationServiceImpl implements TicketNotificationService {

    private final StreamBridge streamBridge;

    @Override
    public void sendToQueue(Ticket ticket) {
        TicketNotification notification = new TicketNotification();
        notification.setAccountId(ticket.getAssignee());
        notification.setTicketId(ticket.getId());
        notification.setTicketDescription(ticket.getDescription());

        // msqueue olarak tanımlanmış binding ismi
        streamBridge.send("sendMessage-out-0", notification);
    }
}
