package com.plannyb.accomodation.service;

import com.plannyb.accomodation.dto.request.HouseRequest;
import com.plannyb.accomodation.dto.request.ImageReq;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.Category;
import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.entity.Image;
import com.plannyb.accomodation.entity.Location;
import com.plannyb.accomodation.host.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HouseService {

    private final HouseRepository houseRepository;
//    private final ImageRepository imageRepository;
//    private final LocationRepository locationRepository;
//    private final CategoryRepository categoryRepository;


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
        house.setPreviewImage(request.getPreviewImage());
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
        category.setCategoryName(request.getCategory().getCategoryName());
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
