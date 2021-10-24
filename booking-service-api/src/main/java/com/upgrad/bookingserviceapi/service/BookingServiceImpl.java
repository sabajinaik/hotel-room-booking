package com.upgrad.bookingserviceapi.service;

import com.upgrad.bookingserviceapi.constants.PaymentMode;
import com.upgrad.bookingserviceapi.dto.BookingDTO;
import com.upgrad.bookingserviceapi.dto.BookingRequestDTO;
import com.upgrad.bookingserviceapi.dto.TransactionDetailsDTO;
import com.upgrad.bookingserviceapi.entity.BookingInfoEntity;
import com.upgrad.bookingserviceapi.feign.PaymentServiceClient;
import com.upgrad.bookingserviceapi.repository.BookingRepo;
import com.upgrad.bookingserviceapi.service.interfaces.BookingService;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.ReadableInstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public BookingDTO createBooking(BookingRequestDTO bookingRequest) throws RuntimeException{
        StringBuilder roomNumbers = new StringBuilder("");
        double roomPrice;

        int totalRoomsRequested = bookingRequest.getNumberOfRooms();

        ReadableInstant fromDateJoda = new DateTime(bookingRequest.getFromDate());
        ReadableInstant toDateJoda = new DateTime(bookingRequest.getToDate());
        int totalStay = Days.daysBetween(fromDateJoda, toDateJoda).getDays() + 1;

        //limiting conditions
        if (totalRoomsRequested <= 0 || totalRoomsRequested > 100) throw new RuntimeException("Incorrect Request for Rooms");
        if (totalStay<=0) throw  new RuntimeException("From Date couldn't be less than To Date");

        ArrayList<String> reservedRooms = getRandomNumbers(bookingRequest.getNumberOfRooms());
        for (String room: reservedRooms) roomNumbers.append(room).append(",");
        roomNumbers = new StringBuilder(StringUtils.strip(roomNumbers.toString(), ","));

        roomPrice = (double)(1000 *  totalRoomsRequested * totalStay);

        BookingDTO bookingDTO = BookingDTO.builder()
                                        .fromDate(bookingRequest.getFromDate())
                                        .toDate(bookingRequest.getToDate())
                                        .aadharNumber(bookingRequest.getAadharNumber())
                                        .roomNumbers(roomNumbers.toString())
                                        .roomPrice(roomPrice)
                                        .bookedOn(new Date())
                                        .build();
        BookingInfoEntity bookingInfoEntity = modelMapper.map(bookingDTO, BookingInfoEntity.class);
        BookingInfoEntity savedBookingInfoEntity = bookingRepo.save(bookingInfoEntity);

        return modelMapper.map(savedBookingInfoEntity, BookingDTO.class);
    }

    @Override
    public BookingDTO getBooking(int bookingId) throws RuntimeException {
        Optional<BookingInfoEntity> bookingInfo = bookingRepo.findById(bookingId);
        if (bookingInfo == null) throw new RuntimeException("Unable to locate the booking Id : "+ bookingId);
        BookingDTO bookingDTO = modelMapper.map(bookingInfo.get(), BookingDTO.class);
        return bookingDTO;
    }

    public TransactionDetailsDTO processPayment(int bookingId, TransactionDetailsDTO transactionDetailsDTO) throws IllegalArgumentException{
        BookingDTO bookingDTO = this.getBooking(bookingId);

        if (!(transactionDetailsDTO.getPaymentMode().equals(PaymentMode.CARD) ||
                transactionDetailsDTO.getPaymentMode().equals(PaymentMode.UPI))) {
            throw new IllegalArgumentException("Invalid mode of payment");
        }

        BookingInfoEntity bookingInfo = modelMapper.map(bookingDTO, BookingInfoEntity.class);
        TransactionDetailsDTO savedTransaction = paymentServiceClient.processPayment(transactionDetailsDTO);
        //update transaction id on the booking
        bookingInfo.setTransactionId(savedTransaction.getId());
        bookingRepo.save(bookingInfo);
        return savedTransaction;
    }

    private ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<>();

        for (int i=0; i<count; i++) numberList.add(String.valueOf(rand.nextInt(upperBound)));
        return numberList;
    }
}
