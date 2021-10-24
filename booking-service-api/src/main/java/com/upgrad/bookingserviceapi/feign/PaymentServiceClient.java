package com.upgrad.bookingserviceapi.feign;

import com.upgrad.bookingserviceapi.dto.TransactionDetailsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "API-GATEWAY")
public interface PaymentServiceClient {
    @GetMapping(value = "${paymentApp.getTransactionPath}")
    public TransactionDetailsDTO checkPaymentServiceStatus(@PathVariable(name = "id") int id);

    @PostMapping(value = "${paymentApp.createPaymentPath}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionDetailsDTO processPayment(@RequestBody TransactionDetailsDTO transactionDetailsDTO);
}