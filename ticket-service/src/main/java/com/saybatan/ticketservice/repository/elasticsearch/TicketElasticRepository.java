package com.saybatan.ticketservice.repository.elasticsearch;

import com.saybatan.ticketservice.entity.elasticsearch.TicketModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel, String> {
}
