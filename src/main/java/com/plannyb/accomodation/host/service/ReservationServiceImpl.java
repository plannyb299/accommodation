package com.plannyb.accomodation.host.service;

import com.plannyb.accomodation.dto.request.ReservationReq;
import com.plannyb.accomodation.dto.response.ReservationRes;
import com.plannyb.accomodation.host.model.Reservation;
import com.plannyb.accomodation.host.processor.ReservationConverter;
import com.plannyb.accomodation.host.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {


    private final ReservationRepository reservationRepository;

    @Override
    public ReservationRes findReservationDtoById(String id) {
        Reservation reservation;
        reservation = reservationRepository.findById(id).get();
        return ReservationConverter.convertToDto(reservation);
    }

    @Override
    public Reservation findReservationById(String id) {
        Reservation reservation;
        reservation = reservationRepository.findById(id).get();
        return reservation;
    }

    @Override
    public List<ReservationRes> findAll() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationRes save(ReservationReq reservationDto) {
        Reservation reservation = ReservationConverter.convert(reservationDto);

        reservation = reservationRepository.save(reservation);

        return ReservationConverter.convertToDto(reservation);
    }

}
