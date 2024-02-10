package com.plannyb.accomodation.host.repository;

import com.plannyb.accomodation.host.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findAll();
    Optional<Reservation> findById(String id);
}