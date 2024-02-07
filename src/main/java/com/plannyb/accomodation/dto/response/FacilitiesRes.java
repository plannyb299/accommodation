package com.plannyb.accomodation.dto.response;

import com.plannyb.accomodation.entity.House;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class FacilitiesRes {

    private String bathrooms;
    private String bedrooms;
    private boolean ac;
    private boolean tv;
    private boolean parking;
    private boolean kitchen;
    private boolean heating;
    private boolean electricity;
    private boolean wifi;
    private boolean elevator;
    private House house;
}
