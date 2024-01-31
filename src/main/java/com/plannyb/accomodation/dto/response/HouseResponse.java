package com.plannyb.accomodation.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HouseResponse {

    private Long id;
    private String street;
    private Integer rooms;
    private Integer bedrooms;
    private Integer bathrooms;
    private String shortAddress;
    private String previewImage;
    private List<ImageRes> images;
    private LocationRes location;
    private List<CategoryRes> categories;
    private BigDecimal price;
    private BigDecimal rent;
    private String description;
    private String neighbourhood;
}
