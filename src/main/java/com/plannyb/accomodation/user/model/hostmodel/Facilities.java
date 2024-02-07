package com.plannyb.accomodation.user.model.hostmodel;

import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.entity.Residency;
import com.plannyb.accomodation.user.model.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "facilities")
@Data
public class Facilities extends AbstractEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "facilities_id", nullable = false, updatable = false)
//    private String id;

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
    @JoinColumn(name = "house_id", insertable=false, updatable=false)
    private House house;
}
