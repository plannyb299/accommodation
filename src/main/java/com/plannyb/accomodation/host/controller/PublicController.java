package com.plannyb.accomodation.host.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.plannyb.accomodation.dto.request.HouseRequest;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.host.model.Reviews;
import com.plannyb.accomodation.host.service.HostService;
import com.plannyb.accomodation.host.model.AllHomesList;
import com.plannyb.accomodation.images.ImageService;
import com.plannyb.accomodation.user.model.User;
import com.plannyb.accomodation.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.plannyb.accomodation.utils.Helpers.convertToJson;

@RestController
@RequestMapping("/api/public")
//@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
public class PublicController {

    @Autowired
    private HostService hostService;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;


    @PostMapping("/home/new")
    public ResponseEntity<String> createHome(@RequestBody HouseRequest myHomePostDto) throws Exception {
//        User user = userService.findByUsername(principal.getName());
//        if(user.getRoles().stream().findFirst().isPresent()||user.getRoles().stream().findFirst().isEmpty())
            return ResponseEntity.ok().body(convertToJson(hostService.save(myHomePostDto)));
//        else
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Status\": \"Error\"}");
    }
    @GetMapping("/homes/all")
    public ResponseEntity<String> getAllHomes()  throws JsonProcessingException {
        return ResponseEntity.ok().body(convertToJson(hostService.findAll()));
    }

    @GetMapping("/homes/byId/{id}")
    public ResponseEntity<HouseResponse> getHomeById(@PathVariable String id)  throws JsonProcessingException {
        HouseResponse house = hostService.findHomeById(id);
        return house != null
                ? new ResponseEntity<>(house, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/homes")
    public ResponseEntity<String> getHomesByFilter(
            @RequestParam String people,
            @RequestParam String latitude,
            @RequestParam String longitude,
            @RequestParam String arrivalDate,
            @RequestParam String departureDate
    ) throws JsonProcessingException, ParseException {
        if(people.isEmpty())people="0";
        if(latitude.isEmpty())latitude="0.0";
        if(longitude.isEmpty())longitude="0.0";
        if(arrivalDate.isEmpty())arrivalDate="1997-01-01";
        if(departureDate.isEmpty())departureDate="1997-01-01";
        Date arrivalDateConverted = new SimpleDateFormat("yyyy-MM-dd").parse(arrivalDate);
        Date departureDateConverted = new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);

        return ResponseEntity.ok().body(convertToJson(hostService.findAllUsingFilters(
                Integer.parseInt(people),
                Double.parseDouble(latitude),
                Double.parseDouble(longitude),
                arrivalDateConverted,
                departureDateConverted
        )));
    }

    @PostMapping("/homes/more")
    public ResponseEntity<String> getHomesByMoreFilters(
            @RequestBody @Nullable AllHomesList allHomesList,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(required = false) Boolean wifi,
            @RequestParam(required = false) Boolean elevator,
            @RequestParam(required = false) Boolean heating,
            @RequestParam(required = false) Boolean kitchen,
            @RequestParam(required = false) Boolean parking,
            @RequestParam(required = false) Boolean tv,
            @RequestParam(required = false) Boolean ac,
            @RequestParam(required = false) String type
    ) throws JsonProcessingException {
        return ResponseEntity.ok().body(
                convertToJson(hostService.findAllUsingMoreFilters(
                        allHomesList,
                        maxPrice,
                        wifi,
                        elevator,
                        heating,
                        kitchen,
                        parking,
                        tv,
                        ac,
                        type
                )));
    }

//    @GetMapping("/home/{id}/image")
//    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws Exception {
//        House myHomeDto = hostService.findHomeDtoById(id);
//
//        if(myHomeDto!=null) {
//            if(myHomeDto.getImage() != null) {
//                byte[] byteArray = new byte[myHomeDto.getImage().length];
//                int i = 0;
//
//                for (Byte wrappedByte : myHomeDto.getImage()) {
//                    byteArray[i++] = wrappedByte; //auto unboxing
//                }
//                response.setContentType("image/jpeg");
//                InputStream is = new ByteArrayInputStream(byteArray);
//                IOUtils.copy(is, response.getOutputStream());
//            }
//        }
//        else {
//            return ResponseEntity.ok().body("{\"Status\": \"Error\"}");
//        }
//    }

//    @PostMapping("user/{id}/image")
//    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){
//
//        imageService.saveImageFileToUser(id, file);
//
//        return "redirect:/user/" + id + "/show";
//    }
//
//    @GetMapping("/home/{id}/reviews")
//    public ResponseEntity<String> reviews(@PathVariable("id") String id) throws Exception {
//        Reviews reviews = hostService.getHomeReviews(id);
//
//        return ResponseEntity.ok().body(convertToJson(reviews));
//    }
}
