package com.plannyb.accomodation.user.service;


import com.plannyb.accomodation.dto.request.HouseRequest;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.user.model.hostmodel.AllHomesList;
import com.plannyb.accomodation.user.model.hostmodel.Reviews;

import java.util.Date;
import java.util.List;


public interface HostService {
    House findHomeDtoById(Long id) throws Exception;

    House findHomeById(Long id);

    List<House> findAll();

    House findByAddress(String address);

    AllHomesList findAllUsingFilters(int people,
                                     double latitude,
                                     double longitude,
                                     Date bookDate,
                                     Date leaveDate);

    AllHomesList findAllUsingMoreFilters(AllHomesList allHomesList,
                                         String maxPrice,
                                         Boolean wifi,
                                         Boolean elevator,
                                         Boolean heating,
                                         Boolean kitchen,
                                         Boolean parking,
                                         Boolean tv,
                                         Boolean ac,
                                         String type
    );

    List<House> findByUserId(Long id);
//    HouseResponse save(HouseResponse HouseResponse);
    HouseResponse save(HouseRequest HousePostDto);

    void deleteById(Long id);

    Reviews getHomeReviews(Long id) throws Exception;
}
