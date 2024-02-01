package com.plannyb.accomodation.user.repository;


import com.plannyb.accomodation.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<House, Long> {
    List<House> findByOwnerId(Long homeId);
    List<House> findAll();
    Optional<House> findById(Long id);

    Optional<House> findByAddress(String address);
}