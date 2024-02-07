package com.plannyb.accomodation.entity;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;

@Entity
@Table(name = "house")
@Data
@Transactional
public class Image extends AbstractEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "image_id", nullable = false, updatable = false)
//    private String id;


    @Column(name = "imageUrl")
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id",insertable = false, updatable = false)
    private House house;
}
