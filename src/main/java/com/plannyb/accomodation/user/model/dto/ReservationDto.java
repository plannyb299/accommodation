//package com.plannyb.accomodation.user.model.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Data
//public class ReservationDto {
//
//    private long reservationId;
//
//    private long bookedHomeId;
//
//    @JsonFormat(pattern="yyyy-MM-dd")
//    @Temporal(TemporalType.DATE)
//    private Date bookedDate;
//
//    @JsonFormat(pattern="yyyy-MM-dd")
//    @Temporal(TemporalType.DATE)
//    private Date leaveDate;
//
//    int isBooked;
//
//    private long userIdBooked;
//    private String userNameBooked;
//
//    int hostReviewStars;
//
//    String hostReviewDescription;
//
//    int homeReviewStars;
//
//    String homeReviewDescription;
//}
