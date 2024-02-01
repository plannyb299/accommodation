package com.plannyb.accomodation.user.model.hostmodel;

import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.entity.Residency;
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
    private String bedrooms;
    private boolean ac;
    private boolean tv;
    private boolean parking;
    private boolean kitchen;
    private boolean heating;
    private boolean electricity;
    private boolean wifi;
    private boolean elevator;
    @OneToOne
    @JoinColumn(name = "id")
    private House house;
}
