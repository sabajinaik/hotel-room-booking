package com.upgrad.paymentserviceapi.repository;

import com.upgrad.paymentserviceapi.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepo extends JpaRepository<TransactionDetailsEntity, Integer> {
}
