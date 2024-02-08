package com.plannyb.accomodation.dto.response;

import com.plannyb.accomodation.dto.request.HouseRequest;
import lombok.Data;

@Data
public class CategoryRes {


    private String categoryName;
    private String homeCategoryTitle;
    private HouseRequest house;
}
