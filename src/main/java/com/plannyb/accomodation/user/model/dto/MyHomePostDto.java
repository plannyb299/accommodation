//package com.plannyb.accomodation.user.model.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.plannyb.accomodation.dto.request.CategoryReq;
//import lombok.Data;
//
//import jakarta.persistence.Lob;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//import java.util.Date;
//import java.util.List;
//
//@Data
//public class MyHomePostDto {
//
//    private long ownerId;
//    private String ownerUsername;
//
//    private List<ReservationDto> reservations;
//
//    @JsonFormat(pattern="yyyy-MM-dd")
//    @Temporal(TemporalType.DATE)
//    private Date openBooking;
//
//    @Temporal(TemporalType.DATE)
//    @JsonFormat(pattern="yyyy-MM-dd")
//    private Date closeBooking;
//
//    @Lob
//    private Byte[] image;
//
//    private double price;
//
//    private String address;
//
//    private String latitude;
//
//    private String longitude;
//
//    private CategoryReq homeCategory;
//
////    private Collection<Picture> pictures = new HashSet<>();
//
//    private String description;
//
//    private Integer squareMeters;
//
//    private double overnightPrice;
//
//    private double extraPersonPrice;
//
//    private Integer maxPeople;
//
//    private Integer minOvernights;
//
//    private Integer beds;
//
//    private Integer bathrooms;
//
//    private Integer bedrooms;
//
//    private String transport;
//
//    private String neighborhood;
//
//    private String houseRules;
//
//    private boolean elevator;
//
//    private boolean heating;
//
//    private boolean kitchen;
//
//    private boolean parking;
//
//    private boolean tv;
//
//    private boolean wifi;
//
//    private boolean ac;
//
//}