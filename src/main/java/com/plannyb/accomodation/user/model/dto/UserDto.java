package com.plannyb.accomodation.user.model.dto;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private long id;

    @Nonnull
    private String username;

    private String firstname;
    private String lastname;

    private String email;

    private String telephone;
    private int approved;

    @Lob
    private Byte[] image;
}