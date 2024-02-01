package com.plannyb.accomodation.user.processor;

import com.plannyb.accomodation.dto.request.HouseRequest;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.House;
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

    public static HouseResponse convertToDto(House house){

        HouseResponse response = new HouseResponse();
        BeanUtils.copyProperties(house, response);

        return response;
    }

    public static House convert(HouseRequest request){

        House house = new House();
        BeanUtils.copyProperties(request, house);

        return house;
    }
}
