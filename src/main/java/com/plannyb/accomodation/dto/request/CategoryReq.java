package com.plannyb.accomodation.dto.request;

import lombok.Data;

@Data
public class CategoryReq {


    private String homeCategoryTitle;

    private HouseRequest house;
}
