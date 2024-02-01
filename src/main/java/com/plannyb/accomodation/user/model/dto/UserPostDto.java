package com.plannyb.accomodation.user.model.dto;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserPostDto {
    private long id;

    private String username;

    private String password;

    private String email;

    private String firstname;
    private String lastname;

    private String telephone;
    private int approved;

    @Lob
    private Byte[] image;
}