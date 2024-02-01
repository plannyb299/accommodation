package com.plannyb.accomodation.entity;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "house")
@Data
@Transactional
public class House extends AbstractEntity{


    @Version
    private Long version;

    @Column(name = "street")
    private String street;

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
    private BigDecimal price;

    @Column(name = "rent")
    private BigDecimal rent;

    @Column(name = "description")
    private String description;

    @Column(name = "neighbourhood")
    private String neighbourhood;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Image> images;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Category category;

}
