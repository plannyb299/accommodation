package com.plannyb.accomodation.dto.request;


import lombok.Data;

@Data
public class LocationReq {

    private String address;

    private String city;

    private HouseRequest house;

}
