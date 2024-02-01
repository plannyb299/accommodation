package com.plannyb.accomodation.user.service;

import com.plannyb.accomodation.dto.request.HouseRequest;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.user.model.hostmodel.AllHomesList;
import com.plannyb.accomodation.user.model.hostmodel.Reservation;
import com.plannyb.accomodation.user.model.hostmodel.Reviews;
import com.plannyb.accomodation.user.processor.HouseProcessor;
import com.plannyb.accomodation.user.repository.HostRepository;
import com.plannyb.accomodation.utils.Helpers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final HouseProcessor houseProcessor;

    @Override
    public HouseResponse findHomeDtoById(Long id) throws Exception {
        House house;
        try {
            house = hostRepository.findById(id).get();
        } catch (NoSuchElementException nsee) {
            throw new Exception("Report not found", nsee.getCause());
        }
        return HouseProcessor.convertToDto(house);
    }

    @Override
    public House findHomeById(Long id) {
        House House;
        House = hostRepository.findById(id).get();
        return House;
    }

    @Override
    public List<HouseResponse> findByUserId(Long id) {
        return hostRepository.findByOwnerId(id)
                .stream()
                .map(HouseProcessor::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public House findByAddress(String address)  {
            House House;
            House = hostRepository.findByAddress(address).get();
        return House;
    }

    @Override
    public HouseResponse save(HouseRequest request) {
        House house = HouseProcessor.convert(request);

        House home = hostRepository.save(house);

        return HouseProcessor.convertToDto(home);
    }

    @Override
    public HouseResponse saveUpdate(HouseRequest housePostDto) {
        House house = HouseProcessor.convert(housePostDto);

        Optional<House> tempHome = hostRepository.findByAddress(housePostDto.getLocation().getAddress());
        house.setId(tempHome.get().getId());

        House house1= hostRepository.save(house);

        return houseProcessor.convertToDto(house1);
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }

    @Override
    public List<HouseResponse> findAll() {
        return hostRepository.findAll()
                .stream()
                .map(HouseProcessor::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AllHomesList findAllUsingFilters(int people, double latitude, double longitude, Date bookDate, Date leaveDate) {
        AllHomesList allHomesList = new AllHomesList();

        List<HouseResponse> tempListWithAllHomes = hostRepository.findAll()
                .stream()
                .map(HouseProcessor::convertToDto)
                .collect(Collectors.toList());

        //filter homes by max people
        List<House> filteredHomeListWithMaxPeople = filterHomeListByMaxPeople(people, tempListWithAllHomes);

        //filter homes by distance by range search
        List<House> filteredHomeListByDistance = filterHomeListByDistance(filteredHomeListWithMaxPeople, latitude, longitude);

        //filter homes by openBooking and closeBooking days
        List<House> filteredHomeListByDates = filterHomeListByDates(bookDate, leaveDate, filteredHomeListByDistance);

        //filter homes the checking the other reservations
        List<House> filteredHomeListByReservationDates = filterHomeListByReservationDates(bookDate, leaveDate, filteredHomeListByDates);

        //sort by price
        List<House> sortedHomesByPrice = sortHomesByPrice(filteredHomeListByReservationDates);

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
    ){
        //filter homes by max price
        if(maxPrice!=null){
            allHomesList.setHomes(filterHomeListByMaxPrice(Double.parseDouble(maxPrice), allHomesList.getHomes()));
        }
        if(wifi!=null){
            allHomesList.setHomes(filterHomeListByWifi(allHomesList.getHomes(),wifi));
        }
        if(elevator!=null){
            allHomesList.setHomes(filterHomeListByElevator(allHomesList.getHomes(),elevator));
        }
        if(heating!=null){
            allHomesList.setHomes(filterHomeListByHeating(allHomesList.getHomes(),heating));
        }
        if(kitchen!=null){
            allHomesList.setHomes(filterHomeListByKitchen(allHomesList.getHomes(),kitchen));
        }
        if(parking!=null){
            allHomesList.setHomes(filterHomeListByParking(allHomesList.getHomes(),parking));
        }
        if(tv!=null){
            allHomesList.setHomes(filterHomeListByTv(allHomesList.getHomes(),tv));
        }
        if(ac!=null){
            allHomesList.setHomes(filterHomeListByAc(allHomesList.getHomes(),ac));
        }
        if(type!=null){
            allHomesList.setHomes(filterHomeListByHomeType(allHomesList.getHomes(),type));
        }
        return allHomesList;
    }

    private List<House> filterHomeListByHomeType(List<House> tempListWithAllHomes, String homeTypeName) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getCategory().getCategoryName().equals(homeTypeName))
                .collect(Collectors.toList());
    }

    private List<House> filterHomeListByAc(List<House> tempListWithAllHomes, Boolean ac) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isAc()==ac)
                .collect(Collectors.toList());
    }

    private List<House> filterHomeListByTv(List<House> tempListWithAllHomes, Boolean tv) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isTv()==tv)
                .collect(Collectors.toList());
    }

    private List<House> filterHomeListByParking(List<House> tempListWithAllHomes, Boolean parking) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isParking()==parking)
                .collect(Collectors.toList());
    }

    private List<House> filterHomeListByKitchen(List<House> tempListWithAllHomes, Boolean kitchen) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isKitchen()==kitchen)
                .collect(Collectors.toList());
    }

    private List<House> filterHomeListByHeating(List<House> tempListWithAllHomes, Boolean heating) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isHeating()==heating)
                .collect(Collectors.toList());
    }

    private List<House> filterHomeListByWifi(List<House> tempListWithAllHomes, Boolean wifi) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isElevator() ==wifi)
                .collect(Collectors.toList());
    }

    private List<House> filterHomeListByElevator(List<House> tempListWithAllHomes, Boolean elevator) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isElevator()==elevator)
                .collect(Collectors.toList());
    }

    private List<House> filterHomeListByMaxPrice(Double maxPrice, List<House> tempListWithAllHomes) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getPrice()<=maxPrice)
                .collect(Collectors.toList());
    }

    private List<House> sortHomesByPrice(List<House> tempListWithAllHomes) {
        return tempListWithAllHomes.stream()
                .sorted(Comparator.comparingDouble(House::getPrice))
                .collect(Collectors.toList());
    }

//    private List<House> filterHomeListByMaxPeople(int people, List<House> tempListWithAllHomes) {
//        return tempListWithAllHomes.stream()
//                .filter(t->t.getMaxPeople()>=people)
//                .collect(Collectors.toList());
//    }

//    private List<House> filterHomeListByReservationDates(Date imerominiaAfixis, Date imerominiaAnaxwrisis, List<House> tempListWithAllHomes) {
//        List<House> filteredList = new ArrayList<>();
//        int einaiHImerominiaAfixisPrinTinImerominiaAfixisApoDB = 0;
//        int einaiHImerominiaAfixisMetaTinImerominiaAnaxwrisisApoDB = 0;
//        int einaiHImerominiaAnaxwrisisPrinTinImerominiaAfixisApoDB = 0;
//        int einaiHImerominiaAnaxwrisisMetaTinImerominiaAnaxwrisisApoDB = 0;
//
//        for(int i=0; i<tempListWithAllHomes.size(); i++){
//
//            //an den iparxei kratisi gia to spiti tote mporei na ginei book opoiadhpote hmeromhnia
//            if(tempListWithAllHomes.get(i).getReservations().isEmpty()) {
//                filteredList.add(tempListWithAllHomes.get(i));
//            }
//
//            for(int j=0; j<tempListWithAllHomes.get(i).getReservations().size(); j++) {
//                einaiHImerominiaAfixisPrinTinImerominiaAfixisApoDB = checkBookingArrivalInReservations(imerominiaAfixis, tempListWithAllHomes.get(i).getReservations(), j);
//                einaiHImerominiaAfixisMetaTinImerominiaAnaxwrisisApoDB = checkBookingLeaveInReservations(imerominiaAfixis, tempListWithAllHomes.get(i).getReservations(), j);
//
//                einaiHImerominiaAnaxwrisisPrinTinImerominiaAfixisApoDB = checkBookingArrivalInReservations(imerominiaAnaxwrisis, tempListWithAllHomes.get(i).getReservations(), j);
//                einaiHImerominiaAnaxwrisisMetaTinImerominiaAnaxwrisisApoDB = checkBookingLeaveInReservations(imerominiaAnaxwrisis, tempListWithAllHomes.get(i).getReservations(), j);
//            }
//
//            if ((einaiHImerominiaAfixisPrinTinImerominiaAfixisApoDB > 0 || einaiHImerominiaAfixisMetaTinImerominiaAnaxwrisisApoDB < 0) &&
//                        (einaiHImerominiaAnaxwrisisPrinTinImerominiaAfixisApoDB > 0 || einaiHImerominiaAnaxwrisisMetaTinImerominiaAnaxwrisisApoDB < 0 )) {
//                    filteredList.add(tempListWithAllHomes.get(i));
//            }
//        }
//        return filteredList;
//    }

//    private int checkBookingArrivalInReservations(Date bookDate, List<ReservationDto> reservationDtoList, int i) {
//        return reservationDtoList
//                .get(i)
//                .getBookedDate()
//                .compareTo(bookDate);
//    }

//    private int checkBookingLeaveInReservations(Date bookDate, List<ReservationDto> reservationDtoList, int i) {
//        return reservationDtoList
//                .get(i)
//                .getLeaveDate()
//                .compareTo(bookDate);
//    }

//    private List<House> filterHomeListByDates(Date bookDate, Date leaveDate, List<House> tempListWithAllHomes) {
//        List<House> filteredList = new ArrayList<>();
//
//        for(int i=0; i<tempListWithAllHomes.size(); i++){
//            int isBookDateAfterOpenBooking = checkBookingArrival(bookDate, tempListWithAllHomes, i);
//            int isLeaveDateBeforeCloseBooking = checkBookingLeave(leaveDate, tempListWithAllHomes, i);
//
//            if(isBookDateAfterOpenBooking<=0 && isLeaveDateBeforeCloseBooking>=0) {
//                filteredList.add(tempListWithAllHomes.get(i));
//            }
//
//        }
//        return filteredList;
//    }

//    private int checkBookingArrival(Date bookDate, List<House> tempListWithAllHomes, int i) {
//        return tempListWithAllHomes
//                        .get(i)
//                        .getOpenBooking()
//                        .compareTo(bookDate);
//    }
//
//    private int checkBookingLeave(Date bookDate, List<House> tempListWithAllHomes, int i) {
//        return tempListWithAllHomes
//                        .get(i)
//                        .getCloseBooking()
//                        .compareTo(bookDate);
//    }


    private List<House> filterHomeListByDistance(List<House> homeList, double givenLat, double givenLong) {
        double maxDistance = 30; //kilometers
        List<House> filteredHomes = homeList.stream()
                .map(home -> {
                    double distanceFromEachHome = Helpers.distance(Double.parseDouble(home.getLocation().getLatitude()),Double.parseDouble(home.getLocation().getLongitude()), givenLat, givenLong, "K");
                    System.out.println("distance Between visitor search and actual Home "+ distanceFromEachHome);
                    if(distanceFromEachHome < maxDistance)
                        return home;
                    else
                        return null;
                })
                .collect(Collectors.toList());

        while (filteredHomes.remove(null));

        if(filteredHomes.isEmpty() || filteredHomes==null)
            return Collections.emptyList();
        else
            return filteredHomes;
    }

    @Override
    public Reviews getHomeReviews(Long id) throws Exception {
        Reviews reviews= new Reviews();
        reviews.setReviews(new ArrayList<>());

        House House = findHomeById(id);

        House.getReservations().forEach(t-> {
            if(t.getHomeReviewStars()!=null) {
                reviews.getReviews().add(t.getHomeReviewStars());
            }
        });

        reviews.setTotalReviews(House.getReservations().stream().filter(r->r.getHomeReviewStars()!=null).count());

        House.getReservations().stream()
                .mapToInt(Reservation::getHomeReviewStars)
                .average()
                .ifPresent(reviews::setAverage);

        return reviews;
    }
}
