package com.upgrad.bookingserviceapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
public class BookingInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookingId")
    private int id;

    @Column(name = "fromDate")
    private Date fromDate;

    @Column(name = "toDate")
    private Date toDate;

    @Column(name = "aadharNumber")
    private String aadharNumber;

    @Column(name = "roomNumbers")
    private String roomNumbers;

    @Column(name = "roomPrice", nullable = false)
    private double roomPrice;

    @Column(name = "transactionId", columnDefinition = "integer default 0")
    private int transactionId = 0;

    @Column(name = "bookedOn")
    private Date bookedOn;
}
