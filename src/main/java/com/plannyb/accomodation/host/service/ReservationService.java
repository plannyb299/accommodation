package com.project.homerent.service;


import com.project.homerent.model.dto.HomeCategoryDto;
import com.project.homerent.model.dto.MyHomeDto;
import com.plannyb.accomodation.dto.request.ReservationDto;
import com.project.homerent.model.hostmodel.HomeCategory;
import com.plannyb.accomodation.host.model.Reservation;

import java.util.List;


public interface ReservationService {
    ReservationDto findReservationDtoById(Long id);
    Reservation findReservationById(Long id);
    List<ReservationDto> findAll();
    ReservationDto save(ReservationDto reservationDto);
}
