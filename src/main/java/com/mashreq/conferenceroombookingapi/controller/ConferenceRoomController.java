package com.mashreq.conferenceroombookingapi.controller;

import com.mashreq.conferenceroombookingapi.model.entity.Booking;
import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;
import com.mashreq.conferenceroombookingapi.service.BookingService;
import com.mashreq.conferenceroombookingapi.service.BookingServiceImpl;
import com.mashreq.conferenceroombookingapi.service.ConferenceRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class ConferenceRoomController {
    private  final ConferenceRoomService conferenceRoomService;
    private  final BookingService bookingService;
    @GetMapping
    public List<ConferenceRoom> getAllBookings(){
        return conferenceRoomService.getAll();
    }
    @GetMapping("/available")
    public List<ConferenceRoom> findAvailableRooms(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startTime,
                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endTime) {

        return bookingService.getAvailableRooms(startTime,endTime);

    }

}
