package com.plannyb.accomodation.controller;

import com.plannyb.accomodation.dto.request.HouseRequest;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/houses")
public class HouseController {

    private final HouseService houseService;

    @GetMapping("getAll")
    public List<HouseResponse> getAllHouses() {
        return houseService.getAllHouses();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HouseResponse> getHouseById(@PathVariable String id) {
        HouseResponse house = houseService.getHouseById(id);
        return house != null
                ? new ResponseEntity<>(house, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveHouse(@RequestBody HouseRequest house) {
        Object savedHouse = houseService.saveHouse(house);
        return new ResponseEntity<>(savedHouse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HouseResponse> updateHouse(@PathVariable String id, @RequestBody HouseRequest updatedHouse) {
        HouseResponse updated = houseService.updateHouse(id, updatedHouse);
        return updated != null
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHouse(@PathVariable String id) {
        houseService.deleteHouse(id);
        return ResponseEntity.ok( "Successfully deleted");
    }
}
