package com.mashreq.conferenceroombookingapi.service;

import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTime;
import com.mashreq.conferenceroombookingapi.repository.MaintenanceTimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingValidatorTest {

    @InjectMocks
    private BookingValidator bookingValidator;

    @Mock
    private MaintenanceTimeRepository maintenanceTimeRepository;

    // Create a custom DateTimeFormatter with the 24-hour format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    List<MaintenanceTime> maintenanceTimes = Arrays.asList(
            new MaintenanceTime(LocalTime.of(9, 0),LocalTime.of(9,15 )),
            new MaintenanceTime(LocalTime.of(13, 0),LocalTime.of(13,15 )),
            new MaintenanceTime(LocalTime.of(17, 0),LocalTime.of(17,15 ))
    );
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void isMaintainanceTimeConflict() {
        LocalDateTime startTime = LocalDateTime.parse("2023-11-06 09:00", formatter);
        LocalDateTime endTime  =  LocalDateTime.parse("2023-11-06 09:15", formatter);

        Mockito.when(maintenanceTimeRepository.findAll()).thenReturn(maintenanceTimes);

        boolean result = bookingValidator.isMaintainanceTimeConflict(startTime, endTime);

        assertTrue(result);

    }
    @Test
    public void testIsMaintainanceTimeConflict_NoConflict() {
        LocalDateTime startTime = LocalDateTime.parse("2023-11-06 09:15", formatter);
        LocalDateTime endTime  =  LocalDateTime.parse("2023-11-06 09:30", formatter);


        Mockito.when(maintenanceTimeRepository.findAll()).thenReturn(maintenanceTimes);

        boolean result = bookingValidator.isMaintainanceTimeConflict(startTime, endTime);

        assertFalse(result);
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
        LocalDateTime startTime = LocalDateTime.parse("2023-11-05 14:00", formatter);
        LocalDateTime endTime  =  LocalDateTime.parse("2023-11-06 14:30", formatter);
        boolean result= bookingValidator.isBookingValidCurrentDate(startTime,endTime);
        assertFalse(result);
    }
}