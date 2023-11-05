package com.mashreq.conferenceroombookingapi.service;

import com.mashreq.conferenceroombookingapi.exception.BookingException;
import com.mashreq.conferenceroombookingapi.exception.RoomsNotAvailableException;
import com.mashreq.conferenceroombookingapi.model.dto.BookingRequest;
import com.mashreq.conferenceroombookingapi.model.entity.Booking;
import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;
import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTiming;
import com.mashreq.conferenceroombookingapi.repository.BookingRepository;
import com.mashreq.conferenceroombookingapi.repository.ConferenceRoomRepository;
import com.mashreq.conferenceroombookingapi.repository.MaintenanceTimingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;
    private final MaintenanceTimingRepository maintenanceTimingRepository;
    private final BookingValidator bookingValidator;
    @Override
    public String bookConferenceRoom(BookingRequest bookingRequest) {
        if(!bookingValidator.isBookingValidCurrentDate(bookingRequest.getStartDateTime(), bookingRequest.getEndDatetime())){
            throw   new BookingException("Booking can be done only for the current date");
        }
        if(!bookingValidator.isValidBookingInterval(bookingRequest.getStartDateTime(), bookingRequest.getEndDatetime())){
            throw   new BookingException("Invalid Booking interval. Bookings must be in 15-minute intervals");
        }
        if(bookingValidator.isMaintainanceTimeConflict(bookingRequest.getStartDateTime(), bookingRequest.getEndDatetime())){
            throw   new BookingException("Rooms Not Available");
        }

        List<ConferenceRoom> availableRooms = getAvailableRooms(bookingRequest.getStartDateTime(), bookingRequest.getEndDatetime());
        if(availableRooms.isEmpty()){
            throw   new RoomsNotAvailableException("Rooms Not Available");
        }
        ConferenceRoom conferenceRoom= availableRooms.stream()
                .filter(room -> bookingRequest.getParticipants() <= room.getCapacity())
                .min(Comparator.comparing(ConferenceRoom::getCapacity))
                .orElseThrow(() -> new BookingException("No Room to Accommodate the participates provided"));

        Booking  booking =dtoToEntity(bookingRequest,conferenceRoom);
        bookingRepository.save(booking);

        return "Room booked successfully";
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<ConferenceRoom> getAvailableRooms(LocalDateTime startTime, LocalDateTime endTime) {

       List<ConferenceRoom>  bookedConferenceRooms=getBookedConferenceRoom(startTime, endTime);
       List<ConferenceRoom>  allConferenceRooms=conferenceRoomRepository.findAll();
       if( bookedConferenceRooms.isEmpty()){
            return  allConferenceRooms;
       }

       List<ConferenceRoom> filteredList =allConferenceRooms .stream()
                .filter(conferenceRoom -> ! bookedConferenceRooms .contains(conferenceRoom))
                .toList();

        return filteredList;
    }
    private Booking dtoToEntity(BookingRequest bookingRequest, ConferenceRoom conferenceRoom){

        return Booking.builder()
                .conferenceRoom(conferenceRoom)
                .participants( bookingRequest.getParticipants())
                .startTime( bookingRequest.getStartDateTime())
                .endTime( bookingRequest.getEndDatetime())
                .build();
    }
    public List<ConferenceRoom> getBookedConferenceRoom(LocalDateTime startTime, LocalDateTime endTime){
        List<Booking>  bookings=bookingRepository.findAll();
        if(bookings.isEmpty()){
            return  Collections.emptyList();
        }
        List<ConferenceRoom> bookedConferenceRooms=new ArrayList<>();
        for(Booking booking:bookings){
            if(booking.getStartTime().equals(startTime)){
                bookedConferenceRooms.add(booking.getConferenceRoom());
            }
            if(startTime.isAfter(booking.getStartTime())&& startTime.isBefore(booking.getEndTime())){
                bookedConferenceRooms.add(booking.getConferenceRoom());
            }
            if(startTime.isBefore(booking.getStartTime())&& endTime.isAfter(booking.getStartTime())){
                bookedConferenceRooms.add(booking.getConferenceRoom());
            }

        }

        return bookedConferenceRooms;
    }
    public void validateMaintainanceTime(LocalDateTime startTime, LocalDateTime endTime){
        List<MaintenanceTiming> maintenanceTimings = maintenanceTimingRepository.findAll();

        boolean isMaintenanceConflict = maintenanceTimings.stream()
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

        if (isMaintenanceConflict) {
            throw new BookingException("Room Can't Be Booked During Maintenance");
        }
    }


}
