package com.plannyb.accomodation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "location")
@Transactional
public class Location extends AbstractEntity{


    @Column(name = "address")
    private String address;

    @Column(nullable = false)
    private String city;

    @OneToOne(mappedBy = "location")
    private House house;

}
