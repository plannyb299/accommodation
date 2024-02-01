package com.plannyb.accomodation.user.model.hostmodel;

import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.House;

import java.util.ArrayList;
import java.util.List;

public class AllHomesList {
    private List<House> homes;

    public AllHomesList(){
        homes = new ArrayList<House>();
    }

    public void add(House home){
        homes.add(home);
    }

    public List<House> getHomes() {
        return homes;
    }

    public void setHomes(List<House> homes) {
        this.homes = homes;
    }
}
