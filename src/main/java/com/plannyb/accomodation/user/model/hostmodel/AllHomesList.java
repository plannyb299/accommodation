package com.plannyb.accomodation.user.model.hostmodel;

import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.House;

import java.util.ArrayList;
import java.util.List;

public class AllHomesList {
    private List<HouseResponse> homes;

    public AllHomesList(){
        homes = new ArrayList<HouseResponse>();
    }

    public void add(HouseResponse home){
        homes.add(home);
    }

    public List<HouseResponse> getHomes() {
        return homes;
    }

    public void setHomes(List<HouseResponse> homes) {
        this.homes = homes;
    }
}
