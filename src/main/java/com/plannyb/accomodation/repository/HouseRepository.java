package com.plannyb.accomodation.repository;


import com.plannyb.accomodation.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {

}