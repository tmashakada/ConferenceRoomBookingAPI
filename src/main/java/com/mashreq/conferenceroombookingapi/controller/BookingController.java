package com.mashreq.conferenceroombookingapi.controller;

import com.mashreq.conferenceroombookingapi.model.dto.BookingRequest;
import com.mashreq.conferenceroombookingapi.model.entity.Booking;
import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;
import com.mashreq.conferenceroombookingapi.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

   private final BookingService bookingService;
    @PostMapping
    public ResponseEntity<String> bookConferenceRoom(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok(bookingService.bookConferenceRoom(bookingRequest));

    }
    @GetMapping
    public List<Booking> getAllBookings(){
       return bookingService.getAllBookings();
    }

    @GetMapping("/available")
    public List<ConferenceRoom> findAvailableRooms(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startTime,
                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endTime) {

          return bookingService.getAvailableRooms(startTime,endTime);

    }
}
