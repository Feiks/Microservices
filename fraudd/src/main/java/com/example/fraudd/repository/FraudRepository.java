package com.example.fraudd.repository;

import com.example.fraudd.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
