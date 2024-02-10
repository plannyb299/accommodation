package com.plannyb.accomodation.host.service;

import com.plannyb.accomodation.dto.request.ReservationReq;
import com.plannyb.accomodation.dto.response.ReservationRes;
import com.plannyb.accomodation.host.model.Reservation;

import java.util.List;


public interface ReservationService {
    ReservationRes findReservationDtoById(String id);
    Reservation findReservationById(String id);
    List<ReservationRes> findAll();
    ReservationRes save(ReservationReq reservationDto);
}
