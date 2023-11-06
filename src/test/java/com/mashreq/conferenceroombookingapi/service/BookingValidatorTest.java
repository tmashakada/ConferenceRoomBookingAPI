package com.mashreq.conferenceroombookingapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class BookingValidatorTest {

    @InjectMocks
    private BookingValidator bookingValidator;

    // Create a custom DateTimeFormatter with the 24-hour format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void isMaintainanceTimeConflict() {

    }

    @Test
    void isValidBookingInterval() {
        LocalDateTime startTime = LocalDateTime.parse("2023-11-06 14:00", formatter);
        LocalDateTime endTime  =  LocalDateTime.parse("2023-11-06 14:30", formatter);
        LocalDateTime startTimeInvalid = LocalDateTime.parse("2023-11-06 14:00", formatter);
        LocalDateTime endTimeInvalid  =  LocalDateTime.parse("2023-11-06 14:50", formatter);
        LocalDateTime startTimeInvalid2 = LocalDateTime.parse("2023-11-06 14:15", formatter);
        LocalDateTime endTimeInvalid2  =  LocalDateTime.parse("2023-11-06 13:45", formatter);
        boolean resultValid=bookingValidator.isValidBookingInterval(startTime,endTime);
        boolean resultInValid=bookingValidator.isValidBookingInterval(startTimeInvalid,endTimeInvalid );
        boolean resultInValid2=bookingValidator.isValidBookingInterval(startTimeInvalid2,endTimeInvalid2 );
        assertTrue(resultValid);
        assertFalse(resultInValid);
        assertFalse(resultInValid2);

    }

    @Test
    void isBookingValidCurrentDate() {

    }
}