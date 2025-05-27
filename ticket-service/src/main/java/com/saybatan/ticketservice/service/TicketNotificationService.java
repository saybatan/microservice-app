package com.saybatan.ticketservice.service;

import com.saybatan.ticketservice.entity.Ticket;

public interface TicketNotificationService {

    void sendToQueue(Ticket ticket);
}
