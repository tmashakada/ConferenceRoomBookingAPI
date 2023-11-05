package com.mashreq.conferenceroombookingapi.service;

import com.mashreq.conferenceroombookingapi.model.dto.BookingRequest;
import com.mashreq.conferenceroombookingapi.model.entity.Booking;
import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    String bookConferenceRoom(BookingRequest bookingRequest);
    List<Booking> getAllBookings();
    List<ConferenceRoom> getAvailableRooms(LocalDateTime startTime, LocalDateTime endTime);
}
