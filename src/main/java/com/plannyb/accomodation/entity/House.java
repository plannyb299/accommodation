package com.plannyb.accomodation.entity;


import com.plannyb.accomodation.user.model.User;
import com.plannyb.accomodation.host.model.Facilities;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "house")
@Data
@Transactional
public class House extends AbstractEntity{


    @Version
    private Long version;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name ="owner_id", nullable = false)
    private User owner;

    @Column(name = "street")
    private String street;

    @Column(name = "address")
    private String address;

    @Column(name = "rooms")
    private Integer rooms;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @Column(name = "shortAddress")
    private String shortAddress;

    @Column(name = "preview_image")
    private String previewImage;

    @Column(name = "price")
    private Double price;

    @Column(name = "rent")
    private Double rent;

    @Column(name = "description")
    private String description;

    @Column(name = "neighbourhood")
    private String neighbourhood;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Image> images;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id",insertable=false, updatable=false)
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id",insertable=false, updatable=false)
    private Facilities facilities;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id",insertable=false, updatable=false)
    private Category category;

//    @OneToMany(mappedBy = "bookedHome", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    private List<Reservation> reservations;

}
