package com.plannyb.accomodation.host.service;


import com.plannyb.accomodation.dto.request.HouseRequest;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.user.model.hostmodel.AllHomesList;
import com.plannyb.accomodation.user.model.hostmodel.Reviews;

import java.util.Date;
import java.util.List;


public interface HostService {
    House findHomeDtoById(String id) throws Exception;

    House findHomeById(String id);

    List<HouseResponse> findAll();

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

    List<HouseResponse> findByUserId(String id);
//    HouseResponse save(HouseResponse HouseResponse);
    HouseResponse save(HouseRequest HousePostDto);

    HouseResponse saveUpdate(HouseRequest HousePostDto);

    void deleteById(String id);

//    Reviews getHomeReviews(String id) throws Exception;
}
