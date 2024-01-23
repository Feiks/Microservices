package com.example.fraudd.service;

import com.example.fraudd.FraudCheckHistory;
import com.example.fraudd.repository.FraudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudRepository fraudRepository;
    public boolean isFrauddulentCustomer(Integer customerId){
        fraudRepository.save(FraudCheckHistory.builder()
                .customerId(customerId)
                .IsFraudster(false)
                .createdAt(LocalDateTime.now())
                .build()

        );
        return false;
    }

}
