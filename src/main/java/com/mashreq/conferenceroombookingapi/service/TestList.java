package com.mashreq.conferenceroombookingapi.service;

import com.mashreq.conferenceroombookingapi.exception.BookingException;
import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;
import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTiming;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestList {
    public static  void main(String [] args){
        // Create a custom DateTimeFormatter with the 24-hour format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Initialize LocalDateTime with a specific date and time in 24-hour format
        LocalDateTime startTime = LocalDateTime.parse("2023-11-06 14:00", formatter);
        LocalDateTime endTime=LocalDateTime.parse("2023-11-05 13:35", formatter);
        boolean sMaintainanceTimeConflict= isMaintainanceTimeConflict(startTime, endTime);
        if(sMaintainanceTimeConflict){
           System.out.println("maintainance Time");
        }else{
            System.out.println("Not maintainance Time");
        }

        if(isValidBookingInterval(startTime, endTime)){
            System.out.println("Valid booking interval");
        }else{
            System.out.println("Invalid booking interval. Bookings must be in 15-minute intervals.");
           // "Invalid booking interval."
            //"Booking is not in valid 15-minute intervals."
           // Invalid Booking interval. Bookings must be in 15-minute intervals
        }
        if(isBookingValidCurrentDate( startTime,  endTime)){
            System.out.println("Booking current date");
        }else{
            System.out.println("Bbooking  NotCurrent");
        }
    }

    public static void removeListFromAnother(){

        List<ConferenceRoom> all = Arrays.asList(
                new ConferenceRoom(1L,"Amaze",5),
                new ConferenceRoom(2L,"Beauty",7),
                new ConferenceRoom(3L,"Inspire",12),
                new ConferenceRoom(4L,"Strive",20)
        );

        List<ConferenceRoom> booked = Arrays.asList(
                new ConferenceRoom(1L,"Amaze",5),
                new ConferenceRoom(2L,"Beauty",7)

        );

        List<ConferenceRoom> filteredList =  all.stream()
                .filter(conferenceRoom -> !booked.contains(conferenceRoom))
                .toList();
        for (ConferenceRoom conferenceRoom:filteredList){
            System.out.println("GGG "+conferenceRoom.getName());
        }
    }
    public  static boolean isMaintainanceTimeConflict(LocalDateTime startTime, LocalDateTime endTime){
        List<MaintenanceTiming> maintenanceTimings=Arrays.asList(
                new MaintenanceTiming(LocalTime.of(9, 0),LocalTime.of(9,15 )),
                new MaintenanceTiming(LocalTime.of(13, 0),LocalTime.of(13,15 )),
                new MaintenanceTiming(LocalTime.of(17, 0),LocalTime.of(17,15 ))
        );

        return maintenanceTimings.stream()
                .anyMatch(maintenanceTiming -> {
                    if (startTime.toLocalTime().equals(maintenanceTiming.getStartTime())) {
                        return true;
                    }
                    if (startTime.toLocalTime().isAfter(maintenanceTiming.getStartTime()) &&
                            startTime.toLocalTime().isBefore(maintenanceTiming.getEndTime())) {
                        return true;
                    }
                    return startTime.toLocalTime().isBefore(maintenanceTiming.getStartTime()) &&
                            endTime.toLocalTime().isAfter(maintenanceTiming.getStartTime());
                });

    }
    // Validation logic for booking intervals
    public  static boolean isValidBookingInterval(LocalDateTime startTime, LocalDateTime endTime) {
        // Check if the start and end times are in 15-minute intervals
        if (startTime.getMinute() % 15 != 0 || endTime.getMinute() % 15 != 0) {
            return false;
        }
        // Check if the end time is after the start time
        if (endTime.isBefore(startTime)) {
            return false;
        }

        // Calculate the duration of the booking
        Duration duration = Duration.between(startTime, endTime);

        // Check if the duration is a multiple of 15 minutes (900 seconds)
        return duration.getSeconds() % 900 == 0;
    }
   public static boolean isValidBookTimeInterval(LocalDateTime startTime, LocalDateTime endTime){
       // Calculate the duration in minutes
       long durationInMinutes = Duration.between(startTime, endTime).toMinutes();

       // Check if the duration is a multiple of 15
       return durationInMinutes % 15 == 0;
   }

   public static boolean isBookingValid(LocalDateTime startTime, LocalDateTime endTime) {


        // Check if the start and end times are in 15-minute intervals
        if (startTime.getMinute() % 15 != 0 || endTime.getMinute() % 15 != 0) {
            return false;
        }

        // Check if the end time is after the start time
        if (endTime.isBefore(startTime)) {
            return false;
        }



       return true;
    }


   // Invalid booking interval.

    public  static boolean isBookingValidCurrentDate(LocalDateTime startTime, LocalDateTime endTime) {

        LocalDate currentDate = LocalDate.now();
        return startTime.toLocalDate().isEqual(currentDate) && endTime.toLocalDate().isEqual(currentDate);
    }
    public static boolean isBookingValidCurrentDate2(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDate currentDate = LocalDate.now();
        return startTime.toLocalDate().isEqual(currentDate) && endTime.toLocalDate().isEqual(currentDate);
    }
    public static boolean isBookingValidCurrentDate3(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startBookingDate = startTime.toLocalDate();
        LocalDate endBookingDate = endTime.toLocalDate();

        return startBookingDate.isEqual(currentDate) && endBookingDate.isEqual(currentDate);
    }


}
