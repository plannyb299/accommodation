package com.plannyb.accomodation.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HouseRequest {

    private Long id;
    private String street;
    private Integer rooms;
    private Integer bedrooms;
    private Integer bathrooms;
    private String shortAddress;
    private String previewImage;
    private List<ImageReq> images;
    private LocationReq location;
    private CategoryReq category;
    private BigDecimal price;
    private BigDecimal rent;
    private String description;
    private String neighbourhood;

}
