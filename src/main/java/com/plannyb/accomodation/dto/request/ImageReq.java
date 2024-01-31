package com.plannyb.accomodation.dto.request;

import lombok.Data;

@Data
public class ImageReq {

    private String imageUrl;
    private HouseRequest house;
}
