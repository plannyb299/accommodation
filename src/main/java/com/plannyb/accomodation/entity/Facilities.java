package com.plannyb.accomodation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "facilities")
@Data
public class Facilities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bathrooms;
    private String parking;
    private String bedrooms;
    @OneToOne
    @JoinColumn(name = "id")
    private Residency residency;
}
