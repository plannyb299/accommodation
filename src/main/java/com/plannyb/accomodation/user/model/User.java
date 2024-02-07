package com.plannyb.accomodation.user.model;

import com.plannyb.accomodation.entity.House;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
@Data
public class User extends AbstractEntity {

    @Nonnull
    private String username;

    @Nonnull
    private String email;

    @Lob
    private Byte[] image;

    @Nonnull
    private String password;

    private String firstName;

    private String lastName;

    private String telephone;

    private int approved;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<House> myHomeList;



    public User() {
    }

    public User(String username, String email, String password, String firstName, String lastName, String telephone, String approved) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.approved = 0;
    }


}