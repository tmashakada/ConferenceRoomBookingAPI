package com.mashreq.conferenceroombookingapi.controller;

import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTime;
import com.mashreq.conferenceroombookingapi.service.MaintenanceTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maintenance-times")
public class MaintenanceTimingController {
    private final MaintenanceTimeService maintenanceTimeService;
    @GetMapping
    public List<MaintenanceTime> getAll(){
        return maintenanceTimeService.getAll();
    }
}
