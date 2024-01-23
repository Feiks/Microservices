package com.example.fraudd.controller;

import com.example.clients.fraud.FraudCheckResponse;
import com.example.fraudd.service.FraudCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService fraudCheckService;
    @PostMapping(path = "/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean frauddulentCustomer = fraudCheckService.isFrauddulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckResponse(frauddulentCustomer);
    }
}
