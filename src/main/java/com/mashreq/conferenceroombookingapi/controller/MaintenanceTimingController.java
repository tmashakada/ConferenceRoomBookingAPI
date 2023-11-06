package com.mashreq.conferenceroombookingapi.controller;

import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTiming;
import com.mashreq.conferenceroombookingapi.service.MaintenanceTimingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maintenance-times")
public class MaintenanceTimingController {
    private final MaintenanceTimingService maintenanceTimingService;
    @GetMapping
    public List<MaintenanceTiming> getAll(){
        return maintenanceTimingService.getAll();
    }
}
