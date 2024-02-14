package com.plannyb.accomodation.user.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String approved;
    private List<String> roles;

}