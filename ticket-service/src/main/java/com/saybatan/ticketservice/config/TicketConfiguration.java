package com.saybatan.ticketservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.saybatan.ticketservice.repository")
@EnableElasticsearchRepositories(basePackages = "com.saybatan.ticketservice.repository.elasticsearch")
@EnableFeignClients(basePackages = "com.saybatan.servicecommon.client")
public class TicketConfiguration {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
