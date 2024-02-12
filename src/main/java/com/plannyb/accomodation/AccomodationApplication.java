package com.plannyb.accomodation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AccomodationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccomodationApplication.class, args);
    }

}
