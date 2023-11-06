package com.mashreq.conferenceroombookingapi.config;

import com.mashreq.conferenceroombookingapi.model.entity.ConferenceRoom;
import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTime;
import com.mashreq.conferenceroombookingapi.repository.ConferenceRoomRepository;
import com.mashreq.conferenceroombookingapi.repository.MaintenanceTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {

    private final MaintenanceTimeRepository maintenanceTimeRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;
    @Override
    public void run(String... args) throws Exception {
        saveMaintenanceTiming();
        saveConferenceRoom();

    }
    private void saveMaintenanceTiming(){
        if(maintenanceTimeRepository.findAll().isEmpty()){

            List<MaintenanceTime> maintenanceTimes =Arrays.asList(
                    new MaintenanceTime(LocalTime.of(9, 0),LocalTime.of(9,15 )),
                    new MaintenanceTime(LocalTime.of(13, 0),LocalTime.of(13,15 )),
                    new MaintenanceTime(LocalTime.of(17, 0),LocalTime.of(17,15 ))
            );
            maintenanceTimeRepository.saveAll(maintenanceTimes);
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
