package com.plannyb.accomodation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "category")
@Transactional
public class Category extends AbstractEntity {


    private String categoryName;

    @OneToOne(mappedBy = "category")
    private House house;
}