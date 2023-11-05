package com.mashreq.conferenceroombookingapi.repository;

import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom,Long> {
    ConferenceRoom findByCapacityLessThanEqual(int capacity);

}
