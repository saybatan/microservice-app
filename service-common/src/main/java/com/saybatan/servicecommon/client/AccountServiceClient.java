package com.saybatan.servicecommon.client;

import com.saybatan.servicecommon.client.contract.AccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("account-service")
public interface AccountServiceClient {

    @RequestMapping("/account/{id}")
    ResponseEntity<AccountResponseDto> getById(@PathVariable("id") String id);

}
