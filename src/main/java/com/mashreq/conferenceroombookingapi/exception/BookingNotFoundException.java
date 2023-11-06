package com.mashreq.conferenceroombookingapi.exception;

public class BookingNotFoundException extends RuntimeException{
    public  BookingNotFoundException(String message) {
        super(message);

    }
}
