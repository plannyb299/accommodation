package com.plannyb.accomodation.host.model;

import com.plannyb.accomodation.entity.House;
import com.plannyb.accomodation.entity.Residency;
import com.plannyb.accomodation.user.model.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "facilities")
@Data
public class Facilities {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.plannyb.accomodation.config.StringIdGenerator")
    @Column(name = "facilities_id", nullable = false, updatable = false)
    private String facilitiesId;

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
    @JoinColumn(name = "id", insertable=false, updatable=false)
    private House house;
}
