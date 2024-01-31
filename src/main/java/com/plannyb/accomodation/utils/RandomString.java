package com.plannyb.accomodation.utils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RandomString {

    private static final AtomicLong idCounter = new AtomicLong(System.currentTimeMillis() * 1000);
    public static String generateId(){

        return UUID.randomUUID().toString();
    }

    public static long generateUniqueId() {

        return idCounter.incrementAndGet() + (long) (Math.random() * 1000);
    }

}
