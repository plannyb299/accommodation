package com.plannyb.accomodation.dto.request;
import lombok.Data;

import java.util.Date;

@Data
public class BookingRequest {

    private String email;
    private Date date;

}
