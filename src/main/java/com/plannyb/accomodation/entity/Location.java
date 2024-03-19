package com.plannyb.accomodation.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "location")
@Transactional
public class Location {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.plannyb.accomodation.config.StringIdGenerator")
    @Column(name = "location_id", nullable = false, updatable = false)
    private String locationId;

    @Column(name = "address")
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @OneToOne(mappedBy = "location")
    private House house;

}
