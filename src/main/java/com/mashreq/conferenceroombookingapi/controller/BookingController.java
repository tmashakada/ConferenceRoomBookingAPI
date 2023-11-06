package com.mashreq.conferenceroombookingapi.controller;

import com.mashreq.conferenceroombookingapi.model.dto.BookingRequest;
import com.mashreq.conferenceroombookingapi.model.entity.Booking;
import com.mashreq.conferenceroombookingapi.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

   private final BookingService bookingService;
    @PostMapping
    public ResponseEntity<String> bookConferenceRoom(@Valid @RequestBody BookingRequest bookingRequest){

        return new ResponseEntity<>(bookingService.bookConferenceRoom(bookingRequest), HttpStatus.CREATED);

    }
    @GetMapping
    public List<Booking> getAllBookings(){
       return bookingService.getAllBookings();
    }
    @DeleteMapping("/{booking_id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("booking_id") Long bookingId){
         bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok("Booking Successful deleted");
    }


}
