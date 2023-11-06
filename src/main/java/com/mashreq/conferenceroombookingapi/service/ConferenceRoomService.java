package com.mashreq.conferenceroombookingapi.service;

import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;

import java.time.LocalDateTime;
import java.util.List;

public interface ConferenceRoomService {
    List<ConferenceRoom> getAll();

}
