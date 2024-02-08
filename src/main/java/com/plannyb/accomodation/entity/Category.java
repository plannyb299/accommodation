package com.plannyb.accomodation.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "category")
@Transactional
public class Category extends AbstractEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "category_id", nullable = false, updatable = false)
//    private String id;


    @Column(name = "home_category_title")
    private String homeCategoryTitle;

    @OneToMany(mappedBy = "category")
    private List<House> houses;
}