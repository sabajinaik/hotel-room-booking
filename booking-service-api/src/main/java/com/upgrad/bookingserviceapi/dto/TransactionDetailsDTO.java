package com.upgrad.bookingserviceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailsDTO {
    private int id;
    private String paymentMode;
    private int bookingId;
    private String upiId;
    private String cardNumber;
}
