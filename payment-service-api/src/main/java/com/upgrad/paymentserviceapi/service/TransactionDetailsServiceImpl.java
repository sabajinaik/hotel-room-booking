package com.upgrad.paymentserviceapi.service;

import com.upgrad.paymentserviceapi.repository.TransactionDetailsRepo;
import com.upgrad.paymentserviceapi.dto.TransactionDetailsDTO;
import com.upgrad.paymentserviceapi.entity.TransactionDetailsEntity;
import com.upgrad.paymentserviceapi.service.interfaces.TransactionDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransactionDetailsRepo transactionDetailsRepo;

    @Override
    public TransactionDetailsDTO createTransaction(TransactionDetailsDTO transactionDetailsDTO) {
        TransactionDetailsEntity transactionDetails = modelMapper.map(transactionDetailsDTO, TransactionDetailsEntity.class);
        transactionDetails = transactionDetailsRepo.save(transactionDetails);
        TransactionDetailsDTO transactionDetailsDTOResponse = modelMapper.map(transactionDetails, TransactionDetailsDTO.class);
        return transactionDetailsDTOResponse;
    }

    @Override
    public TransactionDetailsDTO getTransaction(int transactionId) {
        TransactionDetailsEntity transactionDetailsEntity = transactionDetailsRepo.findById(transactionId)
                .orElseThrow(()->new RuntimeException("Unable to find the data you are looking for id" + transactionId));

        return modelMapper.map(transactionDetailsEntity, TransactionDetailsDTO.class);
    }
}