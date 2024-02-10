package com.plannyb.accomodation.service;

import com.plannyb.accomodation.dto.request.HouseRequest;
import com.plannyb.accomodation.dto.request.ImageReq;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.Category;
import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.entity.Image;
import com.plannyb.accomodation.entity.Location;
import com.plannyb.accomodation.host.model.AllHomesList;
import com.plannyb.accomodation.host.processor.HouseProcessor;
import com.plannyb.accomodation.host.repository.HouseRepository;
import com.plannyb.accomodation.utils.Helpers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HouseService {

    private final HouseRepository houseRepository;
//    private final ImageRepository imageRepository;
//    private final LocationRepository locationRepository;
//    private final CategoryRepository categoryRepository;

    private final HouseProcessor houseProcessor;


    public House findHomeDtoById(String id) throws Exception {
        House house;
        try {
            house = houseRepository.findById(id).get();
        } catch (NoSuchElementException nsee) {
            throw new Exception("Report not found", nsee.getCause());
        }
        return house;
    }


    public House findHomeById(String id) {
        House House;
        House = houseRepository.findById(id).get();
        return House;
    }


    public List<HouseResponse> findByUserId(String id) {
        List<HouseResponse> response= houseRepository.findByOwnerId(id)
                .stream()
                .map(HouseProcessor::convertToDto)
                .collect(Collectors.toList());
        return response;
    }
    public House findByAddress(String address)  {
        House House;
        House = houseRepository.findByAddress(address).get();
        return House;
    }


    public HouseResponse save(HouseRequest request) {
        House house = HouseProcessor.convert(request);

        House home = houseRepository.save(house);

        return HouseProcessor.convertToDto(home);
    }

    public HouseResponse saveUpdate(HouseRequest housePostDto) {
        House house = HouseProcessor.convert(housePostDto);

        Optional<House> tempHome = houseRepository.findByAddress(housePostDto.getLocation().getAddress());
        house.setId(tempHome.get().getId());

        House house1= houseRepository.save(house);

        return houseProcessor.convertToDto(house1);
    }


    public void deleteById(String id) {
        houseRepository.deleteById(id);
    }

    public List<HouseResponse> findAll() {
        return houseRepository.findAll()
                .stream()
                .map(HouseProcessor::convertToDto)
                .collect(Collectors.toList());
    }

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

    private List<HouseResponse> filterHomeListByHomeType(List<HouseResponse> tempListWithAllHomes, String homeTypeName) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getCategory().getCategoryName().equals(homeTypeName))
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByAc(List<HouseResponse> tempListWithAllHomes, Boolean ac) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isAc()==ac)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByTv(List<HouseResponse> tempListWithAllHomes, Boolean tv) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isTv()==tv)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByParking(List<HouseResponse> tempListWithAllHomes, Boolean parking) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isParking()==parking)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByKitchen(List<HouseResponse> tempListWithAllHomes, Boolean kitchen) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isKitchen()==kitchen)
                .collect(Collectors.toList());
    }

    private List<House> filterHomeListByHeating(List<House> tempListWithAllHomes, Boolean heating) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isHeating()==heating)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByWifi(List<HouseResponse> tempListWithAllHomes, Boolean wifi) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isElevator() ==wifi)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByElevator(List<HouseResponse> tempListWithAllHomes, Boolean elevator) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getFacilities().isElevator()==elevator)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByMaxPrice(Double maxPrice, List<HouseResponse> tempListWithAllHomes) {
        return tempListWithAllHomes.stream()
                .filter(t->t.getPrice()<=maxPrice)
                .collect(Collectors.toList());
    }

    private List<HouseResponse> sortHomesByPrice(List<HouseResponse> tempListWithAllHomes) {
        return tempListWithAllHomes.stream()
                .sorted(Comparator.comparingDouble(HouseResponse::getPrice))
                .collect(Collectors.toList());
    }

    private List<HouseResponse> filterHomeListByDistance(List<HouseResponse> homeList, double givenLat, double givenLong) {
        double maxDistance = 30; //kilometers
        List<HouseResponse> filteredHomes = homeList.stream()
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

    public List<HouseResponse> getAllHouses() {

        List<HouseResponse> responseList = new ArrayList<>();

        List<House> houseList= houseRepository.findAll();

        if(houseList != null){
            for(House house : houseList){
                HouseResponse houseResponse = new HouseResponse();
                BeanUtils.copyProperties(house,houseResponse);

                responseList.add(houseResponse);
            }
        }

        return responseList;
    }

    public HouseResponse getHouseById(String id) {

        HouseResponse response = new HouseResponse();
        House house =  houseRepository.findById(id).orElse(null);

        if(house != null) {
            BeanUtils.copyProperties(house, response);
            return response;
        }else {
            return null;
        }
    }

    public House saveHouse(HouseRequest request) {

        House house = new House();
//        house.setHouseId(RandomString.generateUniqueId());
        HouseResponse response = new HouseResponse();
        List<Image> images = new ArrayList<>();


//        imageRepository.saveAll(images);


//        BeanUtils.copyProperties(request,house);
        house.setBathrooms(request.getBathrooms());
        house.setPrice(request.getPrice());
        house.setDescription(request.getDescription());
        house.setRent(request.getRent());
        house.setRooms(request.getRooms());
        house.setStreet(request.getStreet());
        house.setNeighbourhood(request.getNeighbourhood());
        house.setShortAddress(request.getShortAddress());
        house.setImage(request.getImage());
        house.setBedrooms(request.getBedrooms());
//        log.info("House: {}", house.toString());


        for(ImageReq image: request.getImages()){
            Image image1= new Image();
//            BeanUtils.copyProperties(image, image1);
//            image1.setImageId(RandomString.generateUniqueId());
//            image1.setHouseId(house.getHouseId());

            image1.setImageUrl(image.getImageUrl());
//            image1.setHouse(house);
            images.add(image1);
//
        }
//
        house.setImages(images);

        Category category = new Category();

//        category.setCategoryId(RandomString.generateUniqueId());
//        category.setHouse(house);
        category.setHomeCategoryTitle(request.getCategory().getHomeCategoryTitle());
////        categoryRepository.save(category);
//
//
        Location location = new Location();
//        location.setLocationId(RandomString.generateUniqueId());
        location.setAddress(request.getLocation().getAddress());
        location.setCity(request.getLocation().getCity());
//        location.setHouse(house);
//        locationRepository.save(location);
//
        house.setCategory(category);
        house.setLocation(location);

//        log.info("House: {}",house);
        House savedHouse = houseRepository.save(house);

//        BeanUtils.copyProperties(savedHouse, response);

        return savedHouse;

    }

    public HouseResponse updateHouse(String id, HouseRequest updatedHouse) {

        House existingHouse = houseRepository.findById(id).orElse(null);
        HouseResponse response = new HouseResponse();

        if (existingHouse != null) {
            existingHouse.setNeighbourhood(updatedHouse.getNeighbourhood());
            existingHouse.setStreet(updatedHouse.getStreet());
            existingHouse.setRooms(updatedHouse.getRooms());
            existingHouse.setBedrooms(updatedHouse.getBedrooms());
            existingHouse.setBathrooms(updatedHouse.getBathrooms());
            existingHouse.setShortAddress(updatedHouse.getShortAddress());
            existingHouse.setPrice(updatedHouse.getPrice());
            existingHouse.setRent(updatedHouse.getRent());
            existingHouse.setDescription(updatedHouse.getDescription());

            House upDatedHouse = houseRepository.save(existingHouse);
            BeanUtils.copyProperties(upDatedHouse,response);

            return response;
        }

        return null;
    }

    public void deleteHouse(String id) {
        houseRepository.deleteById(id);
    }

}
