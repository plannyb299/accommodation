package com.plannyb.accomodation.host.processor;

import com.plannyb.accomodation.service.HouseService;
import com.plannyb.accomodation.user.service.UserService;
import com.project.homerent.model.dto.ReservationDto;
import com.project.homerent.model.hostmodel.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReservationConverter {

    @Autowired
    private HouseService hostService;

    @Autowired
    private UserService userService;


    public static ReservationDto convertToDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
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

    public static Reservation convert(ReservationDto reservationDto) {
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