package com.mashreq.conferenceroombookingapi.service;

import com.mashreq.conferenceroombookingapi.exception.BookingException;
import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;
import com.mashreq.conferenceroombookingapi.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ConferenceRoomServiceImpl implements ConferenceRoomService {
    private final ConferenceRoomRepository conferenceRoomRepository;
    @Override
    public List<ConferenceRoom> getAll() {
        return conferenceRoomRepository.findAll();
    }


}
