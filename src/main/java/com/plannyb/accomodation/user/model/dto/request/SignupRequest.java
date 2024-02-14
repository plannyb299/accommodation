package com.plannyb.accomodation.user.model.dto.request;

import jakarta.annotation.Nonnull;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    @Nonnull
    private String username;

    @Nonnull
    private String firstname;

    @Nonnull
    private String lastname;

    @Nonnull
    private String email;

    private String telephone;
    private String approved;

    private Set<String> role;

    @Nonnull
    private String password;

}