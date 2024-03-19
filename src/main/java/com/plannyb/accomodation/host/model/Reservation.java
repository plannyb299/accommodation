package com.plannyb.accomodation.host.model;

import com.plannyb.accomodation.entity.AbstractEntity;
import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="reservation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation  {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.plannyb.accomodation.config.StringIdGenerator")
    @Column(name = "reservation_id", nullable = false, updatable = false)
    private String reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="id", nullable = false)
    private House bookedHome;

    @Column(name = "booked_date", nullable = false)
    private LocalDate bookedDate;

    @Column(name = "leave_date", nullable = false)
    private LocalDate leaveDate;

    @Column(name = "booked")
    private int booked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false)
    private User userBooked;

    @Column(name = "host_review_stars")
    private Integer hostReviewStars;

    @Column(name = "host_review_description")
    private String hostReviewDescription;

    @Column(name = "home_review_stars")
    private Integer homeReviewStars;

    @Column(name = "home_review_description")
    private String homeReviewDescription;
}
