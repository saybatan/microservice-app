package com.saybatan.notificationservice.service;

import com.saybatan.servicecommon.messaging.TicketNotification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class NotificationDistributionService {

    @Bean
    public Consumer<TicketNotification> processNotification() {
        return ticketNotification -> {
            System.out.println("———————————————————————————————————————————");
            System.out.println("Notification Alindi Kullanicilara gönderiliyor.");
            System.out.println("Notification -> " + ticketNotification);
        };
    }

}
