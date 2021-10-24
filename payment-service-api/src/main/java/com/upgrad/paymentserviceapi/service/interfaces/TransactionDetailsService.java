package com.upgrad.paymentserviceapi.service.interfaces;

import com.upgrad.paymentserviceapi.dto.TransactionDetailsDTO;

public interface TransactionDetailsService {
    public TransactionDetailsDTO createTransaction(TransactionDetailsDTO transactionDetailsEntity);

    public TransactionDetailsDTO getTransaction(int transactionId);
}
