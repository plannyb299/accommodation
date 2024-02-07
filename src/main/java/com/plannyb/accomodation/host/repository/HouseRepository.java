package com.plannyb.accomodation.host.repository;


import com.plannyb.accomodation.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, String> {

    Optional<House> findByAddress(String address);

    List<House> findByOwnerId(String homeId);
}