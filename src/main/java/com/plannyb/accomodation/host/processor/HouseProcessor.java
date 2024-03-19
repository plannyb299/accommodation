package com.plannyb.accomodation.host.processor;

import com.plannyb.accomodation.dto.request.HouseRequest;
import com.plannyb.accomodation.dto.response.CategoryRes;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.Category;
import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.entity.Location;
import com.plannyb.accomodation.host.model.Facilities;
import com.plannyb.accomodation.host.service.HomeCategoryService;
import com.plannyb.accomodation.user.model.User;
import com.plannyb.accomodation.user.model.dto.UserDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Data
@RequiredArgsConstructor
@Component
public class HouseProcessor {

    private final HomeCategoryService homeCategoryService;

    public static HouseResponse convertToDto(House house){

        HouseResponse response = new HouseResponse();
        BeanUtils.copyProperties(house, response);

        return response;
    }

    public static House convert(HouseRequest request){

        House house = new House();
        BeanUtils.copyProperties(request, house);
        Category category = new Category();
        BeanUtils.copyProperties(request.getCategory(),category);
        house.setCategory(category);
        Location location = new Location();
        BeanUtils.copyProperties(request.getLocation(),location);
        house.setLocation(location);
        Facilities facilities = new Facilities();
        BeanUtils.copyProperties(request.getFacilities(),facilities);
        house.setFacilities(facilities);

        return house;
    }
}
