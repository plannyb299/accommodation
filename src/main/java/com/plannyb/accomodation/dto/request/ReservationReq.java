package com.plannyb.accomodation.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationReq {

    private String reservationId;

    private String bookedHomeId;

    private LocalDate bookedDate;

    private LocalDate leaveDate;

    int isBooked;

    private String userIdBooked;
    private String userNameBooked;

    int hostReviewStars;

    String hostReviewDescription;

    int homeReviewStars;

    String homeReviewDescription;
}
