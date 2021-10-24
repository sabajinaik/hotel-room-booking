package com.upgrad.bookingserviceapi.dto;

import lombok.*;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private int id;
    private Date fromDate;
    private Date toDate;
    private String aadharNumber;
    private String roomNumbers;
    private double roomPrice;
    private int transactionId = 0;      //default transaction id as 0
    private Date bookedOn = new Date(); //default bookedOn as current date
}
