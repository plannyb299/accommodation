package com.plannyb.accomodation.user.model.hostmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="reservation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {
    @Id
    @Column(name = "reservation_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="myhome_id", nullable = false)
    private House bookedHome;

    @Column(name = "booked_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date bookedDate;

    @Column(name = "leave_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date leaveDate;

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
