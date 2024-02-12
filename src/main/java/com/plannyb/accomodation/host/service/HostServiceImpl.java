package com.plannyb.accomodation.host.service;

import com.plannyb.accomodation.dto.request.HouseRequest;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.host.model.AllHomesList;
import com.plannyb.accomodation.host.model.Reservation;
import com.plannyb.accomodation.host.model.Reviews;
import com.plannyb.accomodation.host.processor.HouseProcessor;
import com.plannyb.accomodation.host.repository.HouseRepository;
import com.plannyb.accomodation.utils.Helpers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostServiceImpl implements HostService {

    private final HouseProcessor houseProcessor;
    private final HouseRepository houseRepository;

    @Override
    public House findHomeDtoById(String id) throws Exception {
        House house;
        try {
            house = houseRepository.findById(id).get();
        } catch (NoSuchElementException nsee) {
            throw new Exception("Report not found", nsee.getCause());
        }
        return house;
    }

    @Override
    public House findHomeById(String id) {
        House House;
        House = houseRepository.findById(id).get();
        return House;
    }

    @Override
    public List<HouseResponse> findByUserId(String id) {
        List<HouseResponse> response = houseRepository.findByOwnerId(id)
                .stream()
                .map(HouseProcessor::convertToDto)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public House findByAddress(String address) {
        House House;
        House = houseRepository.findByAddress(address).get();
        return House;
    }

    @Override
    public HouseResponse save(HouseRequest request) {
        House house = HouseProcessor.convert(request);

        House home = houseRepository.save(house);

        return HouseProcessor.convertToDto(home);
    }

    @Override
    public HouseResponse saveUpdate(HouseRequest housePostDto) {
        House house = HouseProcessor.convert(housePostDto);

        Optional<House> tempHome = houseRepository.findByAddress(housePostDto.getLocation().getAddress());
        house.setId(tempHome.get().getId());

        House house1 = houseRepository.save(house);

        return houseProcessor.convertToDto(house1);
    }

    @Override
    public void deleteById(String id) {
        houseRepository.deleteById(id);
    }

    @Override
    public List<HouseResponse> findAll() {
        return houseRepository.findAll()
                .stream()
                .map(HouseProcessor::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AllHomesList findAllUsingFilters(int people, double latitude, double longitude, Date bookDate, Date leaveDate) {
        AllHomesList allHomesList = new AllHomesList();

        List<HouseResponse> tempListWithAllHomes = houseRepository.findAll()
                .stream()
                .map(HouseProcessor::convertToDto)
                .collect(Collectors.toList());


        //filter homes by distance by range search
        List<HouseResponse> filteredHomeListByDistance = filterHomeListByDistance(tempListWithAllHomes, latitude, longitude);


        //sort by price
        List<HouseResponse> sortedHomesByPrice = sortHomesByPrice(filteredHomeListByDistance);

        allHomesList.setHomes(sortedHomesByPrice);
        return allHomesList;
    }

    @Override
    public AllHomesList findAllUsingMoreFilters(AllHomesList allHomesList,
                                                String maxPrice,
                                                Boolean wifi,
                                                Boolean elevator,
                                                Boolean heating,
                                                Boolean kitchen,
                                                Boolean parking,
                                                Boolean tv,
                                                Boolean ac,
                                                String type
    ) {
        //filter homes by max price
        if (maxPrice != null) {
            allHomesList.setHomes(filterHomeListByMaxPrice(Double.parseDouble(maxPrice), allHomesList.getHomes()));
        }
        if (wifi != null) {
            allHomesList.setHomes(filterHomeListByWifi(allHomesList.getHomes(), wifi));
        }
        if (elevator != null) {
            allHomesList.setHomes(filterHomeListByElevator(allHomesList.getHomes(), elevator));
        }
        if (kitchen != null) {
            allHomesList.setHomes(filterHomeListByKitchen(allHomesList.getHomes(), kitchen));
        }
        if (parking != null) {
            allHomesList.setHomes(filterHomeListByParking(allHomesList.getHomes(), parking));
        }
        if (tv != null) {
            allHomesList.setHomes(filterHomeListByTv(allHomesList.getHomes(), tv));
        }
        if (ac != null) {
            allHomesList.setHomes(filterHomeListByAc(allHomesList.getHomes(), ac));
        }
        if (type != null) {
            allHomesList.setHomes(filterHomeListByHomeType(allHomesList.getHomes(), type));
        }
        return allHomesList;
    }

    private List<HouseResponse> filterHomeListByHomeType(List<HouseResponse> tempListWithAllHomes, String homeTypeName) {
        return tempListWithAllHomes.stream()
                .filter(t -> t.getCategory().getCategoryName().equals(homeTypeName))
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByAc(List<HouseResponse> tempListWithAllHomes, Boolean ac) {
        return tempListWithAllHomes.stream()
                .filter(t -> t.getFacilities().isAc() == ac)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByTv(List<HouseResponse> tempListWithAllHomes, Boolean tv) {
        return tempListWithAllHomes.stream()
                .filter(t -> t.getFacilities().isTv() == tv)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByParking(List<HouseResponse> tempListWithAllHomes, Boolean parking) {
        return tempListWithAllHomes.stream()
                .filter(t -> t.getFacilities().isParking() == parking)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByKitchen(List<HouseResponse> tempListWithAllHomes, Boolean kitchen) {
        return tempListWithAllHomes.stream()
                .filter(t -> t.getFacilities().isKitchen() == kitchen)
                .collect(Collectors.toList());
    }

    private List<House> filterHomeListByHeating(List<House> tempListWithAllHomes, Boolean heating) {
        return tempListWithAllHomes.stream()
                .filter(t -> t.getFacilities().isHeating() == heating)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByWifi(List<HouseResponse> tempListWithAllHomes, Boolean wifi) {
        return tempListWithAllHomes.stream()
                .filter(t -> t.getFacilities().isElevator() == wifi)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByElevator(List<HouseResponse> tempListWithAllHomes, Boolean elevator) {
        return tempListWithAllHomes.stream()
                .filter(t -> t.getFacilities().isElevator() == elevator)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByMaxPrice(Double maxPrice, List<HouseResponse> tempListWithAllHomes) {
        return tempListWithAllHomes.stream()
                .filter(t -> t.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> sortHomesByPrice(List<HouseResponse> tempListWithAllHomes) {
        return tempListWithAllHomes.stream()
                .sorted(Comparator.comparingDouble(HouseResponse::getPrice))
                .collect(Collectors.toList());
    }


    private List<House> filterHomeListByReservationDates(LocalDate imerominiaAfixis, LocalDate imerominiaAnaxwrisis, List<House> tempListWithAllHomes) {
        List<House> filteredList = new ArrayList<>();
        int einaiHImerominiaAfixisPrinTinImerominiaAfixisApoDB = 0;
        int einaiHImerominiaAfixisMetaTinImerominiaAnaxwrisisApoDB = 0;
        int einaiHImerominiaAnaxwrisisPrinTinImerominiaAfixisApoDB = 0;
        int einaiHImerominiaAnaxwrisisMetaTinImerominiaAnaxwrisisApoDB = 0;

        for (int i = 0; i < tempListWithAllHomes.size(); i++) {

            //an den iparxei kratisi gia to spiti tote mporei na ginei book opoiadhpote hmeromhnia
            if (tempListWithAllHomes.get(i).getReservations().isEmpty()) {
                filteredList.add(tempListWithAllHomes.get(i));
            }

            for (int j = 0; j < tempListWithAllHomes.get(i).getReservations().size(); j++) {
                einaiHImerominiaAfixisPrinTinImerominiaAfixisApoDB = checkBookingArrivalInReservations(imerominiaAfixis, tempListWithAllHomes.get(i).getReservations(), j);
                einaiHImerominiaAfixisMetaTinImerominiaAnaxwrisisApoDB = checkBookingLeaveInReservations(imerominiaAfixis, tempListWithAllHomes.get(i).getReservations(), j);

                einaiHImerominiaAnaxwrisisPrinTinImerominiaAfixisApoDB = checkBookingArrivalInReservations(imerominiaAnaxwrisis, tempListWithAllHomes.get(i).getReservations(), j);
                einaiHImerominiaAnaxwrisisMetaTinImerominiaAnaxwrisisApoDB = checkBookingLeaveInReservations(imerominiaAnaxwrisis, tempListWithAllHomes.get(i).getReservations(), j);
            }

            if ((einaiHImerominiaAfixisPrinTinImerominiaAfixisApoDB > 0 || einaiHImerominiaAfixisMetaTinImerominiaAnaxwrisisApoDB < 0) &&
                    (einaiHImerominiaAnaxwrisisPrinTinImerominiaAfixisApoDB > 0 || einaiHImerominiaAnaxwrisisMetaTinImerominiaAnaxwrisisApoDB < 0)) {
                filteredList.add(tempListWithAllHomes.get(i));
            }
        }
        return filteredList;
    }

    private int checkBookingArrivalInReservations(LocalDate bookDate, List<Reservation> reservationDtoList, int i) {
        return reservationDtoList
                .get(i)
                .getBookedDate()
                .compareTo(bookDate);
    }

    private int checkBookingLeaveInReservations(LocalDate bookDate, List<Reservation> reservationDtoList, int i) {
        return reservationDtoList
                .get(i)
                .getLeaveDate()
                .compareTo(bookDate);
    }


    private List<HouseResponse> filterHomeListByDistance(List<HouseResponse> homeList, double givenLat, double givenLong) {
        double maxDistance = 30; //kilometers
        List<HouseResponse> filteredHomes = homeList.stream()
                .map(home -> {
                    double distanceFromEachHome = Helpers.distance(Double.parseDouble(home.getLocation().getLatitude()), Double.parseDouble(home.getLocation().getLongitude()), givenLat, givenLong, "K");
                    System.out.println("distance Between visitor search and actual Home " + distanceFromEachHome);
                    if (distanceFromEachHome < maxDistance)
                        return home;
                    else
                        return null;
                })
                .collect(Collectors.toList());

        while (filteredHomes.remove(null)) ;

        if (filteredHomes.isEmpty() || filteredHomes == null)
            return Collections.emptyList();
        else
            return filteredHomes;
    }

    @Override
    public Reviews getHomeReviews(String id) throws Exception {
        Reviews reviews = new Reviews();
        reviews.setReviews(new ArrayList<>());

        House House = findHomeById(id);

        House.getReservations().forEach(t -> {
            if (t.getHomeReviewStars() != null) {
                reviews.getReviews().add(t.getHomeReviewStars());
            }
        });

        reviews.setTotalReviews(House.getReservations().stream().filter(r -> r.getHomeReviewStars() != null).count());

        House.getReservations().stream()
                .mapToInt(Reservation::getHomeReviewStars)
                .average()
                .ifPresent(reviews::setAverage);

        return reviews;
    }
}
