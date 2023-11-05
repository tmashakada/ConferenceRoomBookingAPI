package com.mashreq.conferenceroombookingapi.config;

import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;
import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTiming;
import com.mashreq.conferenceroombookingapi.repository.ConferenceRoomRepository;
import com.mashreq.conferenceroombookingapi.repository.MaintenanceTimingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {

    private final MaintenanceTimingRepository maintenanceTimingRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;
    @Override
    public void run(String... args) throws Exception {
        saveMaintenanceTiming();
        saveConferenceRoom();

    }
    private void saveMaintenanceTiming(){
        if(maintenanceTimingRepository.findAll().isEmpty()){

            List<MaintenanceTiming> maintenanceTimings=Arrays.asList(
                    new MaintenanceTiming(LocalTime.of(9, 0),LocalTime.of(9,15 )),
                    new MaintenanceTiming(LocalTime.of(13, 0),LocalTime.of(13,15 )),
                    new MaintenanceTiming(LocalTime.of(17, 0),LocalTime.of(17,15 ))
            );
            maintenanceTimingRepository.saveAll(maintenanceTimings);
        }



    }
    private void saveConferenceRoom(){

        if(conferenceRoomRepository.findAll().isEmpty()){
            List<ConferenceRoom> conferenceRooms=Arrays.asList(
                    new ConferenceRoom("Amaze",3),
                    new ConferenceRoom("Beauty",7),
                    new ConferenceRoom("Inspire",12),
                    new ConferenceRoom("Strive",20)
            );
            conferenceRoomRepository.saveAll(conferenceRooms);

        }

    }
}
