package com.plannyb.accomodation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ReservationRes {

    private String reservationId;

    private String bookedHomeId;

    private LocalDate bookedDate;

    private LocalDate leaveDate;

    int isBooked;

    private String userIdBooked;
    private String userNameBooked;

    private Integer hostReviewStars;

    private String hostReviewDescription;

    private Integer homeReviewStars;

    private String homeReviewDescription;
}
