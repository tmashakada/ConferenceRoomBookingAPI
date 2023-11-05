package com.mashreq.conferenceroombookingapi.repository;

import com.mashreq.conferenceroombookingapi.model.entity.Booking;
import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByStartTimeAndEndTime(LocalDateTime startTime, LocalDateTime endTime);
}
