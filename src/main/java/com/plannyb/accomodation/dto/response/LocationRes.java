package com.plannyb.accomodation.dto.response;


import com.plannyb.accomodation.entity.House;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class LocationRes {

    private String address;

    private String city;

    private String latitude;

    private String longitude;

    private House house;

}
