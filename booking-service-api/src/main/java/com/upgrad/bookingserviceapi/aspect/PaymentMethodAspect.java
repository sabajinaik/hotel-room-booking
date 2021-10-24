package com.upgrad.bookingserviceapi.aspect;

import com.upgrad.bookingserviceapi.dto.TransactionDetailsDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PaymentMethodAspect {
    @Before(value = "execution(* com.upgrad.bookingserviceapi.service.BookingServiceImpl.processPayment(..)) && args(bookingId, transactionDetailsDTO)")
    public void beforeAdvice(JoinPoint joinPoint, int bookingId, TransactionDetailsDTO transactionDetailsDTO){
        //check transaction type
        if (!(transactionDetailsDTO.getPaymentMode().equals("CARD")||
                transactionDetailsDTO.getPaymentMode().equals("UPI"))) {
            throw new IllegalArgumentException("Invalid Mode Of Payment");
        }
    }
}
