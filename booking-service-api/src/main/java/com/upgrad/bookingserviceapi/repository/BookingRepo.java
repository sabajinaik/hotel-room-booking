package com.upgrad.bookingserviceapi.repository;

import com.upgrad.bookingserviceapi.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<BookingInfoEntity, Integer> {
}
