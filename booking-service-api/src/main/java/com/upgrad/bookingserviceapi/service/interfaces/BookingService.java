package com.upgrad.bookingserviceapi.service.interfaces;

import com.upgrad.bookingserviceapi.dto.BookingDTO;
import com.upgrad.bookingserviceapi.dto.BookingRequestDTO;
import com.upgrad.bookingserviceapi.entity.BookingInfoEntity;

public interface BookingService {
    public BookingDTO createBooking(BookingRequestDTO bookingRequest);

    public BookingDTO getBooking(int bookingId);
}
