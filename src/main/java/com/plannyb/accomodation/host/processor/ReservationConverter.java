package com.plannyb.accomodation.host.processor;

import com.plannyb.accomodation.dto.request.ReservationReq;
import com.plannyb.accomodation.service.HouseService;
import com.plannyb.accomodation.user.service.UserService;
import com.plannyb.accomodation.host.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ReservationConverter {

    private final HouseService hostService;

    private final UserService userService;

    private static HouseService hostServiceStatic;

    private static UserService userServiceStatic;

    @Autowired
    public void setStatic() {
        this.hostServiceStatic = hostService;
        this.userServiceStatic = userService;
    }


    public static ReservationReq convertToDto(Reservation reservation) {
        ReservationReq reservationDto = new ReservationReq();
        reservationDto.setReservationId(reservation.getId());
        reservationDto.setBookedDate(reservation.getBookedDate());
        reservationDto.setLeaveDate(reservation.getLeaveDate());
        reservationDto.setBookedHomeId(reservation.getBookedHome().getId());
        reservationDto.setUserIdBooked(reservation.getUserBooked().getId());
        reservationDto.setUserNameBooked(reservation.getUserBooked().getUsername());

        reservationDto.setHomeReviewDescription(reservation.getHomeReviewDescription());
        if(reservation.getHomeReviewStars()==null)reservationDto.setHomeReviewStars(0);
        else reservationDto.setHomeReviewStars(reservation.getHomeReviewStars());

        reservationDto.setHostReviewDescription(reservation.getHostReviewDescription());

        if(reservation.getHostReviewStars()==null)reservationDto.setHostReviewStars(0);
        else reservationDto.setHostReviewStars(reservation.getHostReviewStars());

        return reservationDto;
    }

    public static Reservation convert(ReservationReq reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getReservationId());
        reservation.setBookedDate(reservationDto.getBookedDate());
        reservation.setLeaveDate(reservationDto.getLeaveDate());
        reservation.setBookedHome(hostServiceStatic.findHomeById(reservationDto.getBookedHomeId()));
        reservation.setUserBooked(userServiceStatic.findById(reservationDto.getUserIdBooked()));

        reservation.setHomeReviewDescription(reservationDto.getHomeReviewDescription());
        reservation.setHomeReviewStars(reservationDto.getHomeReviewStars());
        reservation.setHostReviewDescription(reservationDto.getHostReviewDescription());
        reservation.setHostReviewStars(reservationDto.getHostReviewStars());
        return reservation;
    }
}