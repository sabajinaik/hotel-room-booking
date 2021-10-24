package com.upgrad.bookingserviceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    private Date fromDate;
    private Date toDate;
    private String aadharNumber;
    private int numberOfRooms;
}
