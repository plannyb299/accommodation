package com.plannyb.accomodation.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "category")
public class Category{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.plannyb.accomodation.config.StringIdGenerator")
    @Column(name = "category_id", nullable = false, updatable = false)
    private String categoryId;


    @Column(name = "home_category_title")
    private String homeCategoryTitle;

    @OneToMany(mappedBy = "category")
    private List<House> houses;
}