package com.upgrad.paymentserviceapi.controller;

import com.upgrad.paymentserviceapi.dto.TransactionDetailsDTO;
import com.upgrad.paymentserviceapi.entity.TransactionDetailsEntity;
import com.upgrad.paymentserviceapi.service.TransactionDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment_app/v1")
public class TransactionDetailsController {

    @Autowired
    private TransactionDetailsServiceImpl transactionDetailsService;

    @GetMapping(value = "/status")
    public ResponseEntity checkPaymentServiceStatus(){
        return new ResponseEntity("active", HttpStatus.OK);
    }

    @GetMapping(value="/transaction/{id}")
    public ResponseEntity getPayment(@PathVariable(name = "id") int id){
        return new ResponseEntity(transactionDetailsService.getTransaction(id), HttpStatus.OK);
    }

    @PostMapping(value = "/transaction",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity processPayment(@RequestBody TransactionDetailsDTO transactionDetailsDTO){
        return new ResponseEntity(transactionDetailsService.createTransaction(transactionDetailsDTO), HttpStatus.CREATED);
    }
}
