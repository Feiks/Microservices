package com.example.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "fraud")
public interface FraudClient {

    @PostMapping(path = "/api/v1/fraud-check/{customerId}")
     FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
