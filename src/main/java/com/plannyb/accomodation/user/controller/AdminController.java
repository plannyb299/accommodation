package com.plannyb.accomodation.user.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.plannyb.accomodation.dto.response.HouseResponse;
import com.plannyb.accomodation.user.model.dto.UserDto;
import com.plannyb.accomodation.host.service.HostService;
import com.plannyb.accomodation.user.service.UserService;

import com.project.homerent.util.Helpers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.plannyb.accomodation.utils.Helpers.convertToJson;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private UserService userService;


    private HostService hostService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<String> findUserById(@PathVariable("id") String id) throws JsonProcessingException {
        UserDto userDto = userService.findDtoById(id);
        return ResponseEntity.ok().body(convertToJson(userDto));
    }

    @PutMapping("/users")
    public ResponseEntity<String> updateUser(@RequestBody @Nullable UserDto userDto) throws JsonProcessingException {
            return ResponseEntity.ok().body(convertToJson(userService.save(userDto)));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable("id") String id) {
        userService.deleteById(id);
        return ResponseEntity.ok().body("{\"Status\": \"Successful Deletion\"}");
    }

    @PostMapping("/users/{id}/approve")
    public ResponseEntity<String> ApproveUser(@PathVariable("id") String id) {
        userService.approve(id);

        return ResponseEntity.ok().body("{\"Status\": \"User Approved\"}");
    }

    @GetMapping("/users/unapproved")
    public ResponseEntity<List<UserDto>> findUnapprovedUsers(){
        return ResponseEntity.ok().body(userService.findUnapprovedUsers());
    }

//    @GetMapping("/export/home/{id}/details")
//    public ResponseEntity<String> findHomeDetails(@PathVariable("id") String id, @RequestParam String format) throws Exception {
//        HouseResponse myHomeDto = hostService.findHomeDtoById(id);
//        if(format.equals("xml"))
//            return ResponseEntity.ok().body(Helpers.myHomeDtoToXML(myHomeDto));
//        else if(format.equals("json"))
//            return ResponseEntity.ok().body(Helpers.convertToJson(myHomeDto));
//        return ResponseEntity.ok().body("{\"message\": \"Choose format\"}");
//    }

//    @GetMapping("/export/homes/details")
//    public ResponseEntity<String> findAllHomesDetails(@RequestParam String format) throws Exception {
//        List <HouseResponse> usersHomeList = hostService.findAll();
//        if(format.equals("xml"))
//            return ResponseEntity.ok().body(Helpers.myHomesToXML(usersHomeList));
//        else if(format.equals("json"))
//            return ResponseEntity.ok().body(Helpers.convertToJson(usersHomeList));
//        return ResponseEntity.ok().body("{\"message\": \"Choose format\"}");
//    }
}