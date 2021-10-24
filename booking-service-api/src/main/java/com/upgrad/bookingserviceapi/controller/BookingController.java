package com.upgrad.bookingserviceapi.controller;

import com.upgrad.bookingserviceapi.dto.BookingRequestDTO;
import com.upgrad.bookingserviceapi.dto.TransactionDetailsDTO;
import com.upgrad.bookingserviceapi.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping(value = "/booking_app/v1")
public class BookingController {
    @Autowired
    private BookingServiceImpl bookingService;

    @GetMapping(value = "/status")
    public ResponseEntity checkPaymentServiceStatus(){
        return new ResponseEntity("active", HttpStatus.OK);
    }

    @GetMapping(value = "/booking/{bookingId}")
    public ResponseEntity getBooking(@PathVariable(name = "bookingId") int bookingId){
        return new ResponseEntity(bookingService.getBooking(bookingId), HttpStatus.OK);
    }

    @PostMapping(value = "/booking"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBooking(@RequestBody BookingRequestDTO bookingRequestDTO){
        return new ResponseEntity(bookingService.createBooking(bookingRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/booking/{bookingId}/transaction"
            , produces = MediaType.APPLICATION_JSON_VALUE
            , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity processPayment(@PathVariable(name = "bookingId") int bookingId
            , @RequestBody TransactionDetailsDTO transaction){

        try {
            return new ResponseEntity(bookingService.processPayment(bookingId, transaction),HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            HashMap<String,String> responseOnError = new LinkedHashMap<>();
            responseOnError.put("message","Invalid mode of payment");
            responseOnError.put("statusCode", "400");
            return new ResponseEntity(responseOnError,HttpStatus.BAD_REQUEST);
        }
    }
}
