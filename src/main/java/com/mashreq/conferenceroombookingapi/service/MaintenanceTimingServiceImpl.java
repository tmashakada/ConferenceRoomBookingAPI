package com.mashreq.conferenceroombookingapi.service;

import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTiming;
import com.mashreq.conferenceroombookingapi.repository.MaintenanceTimingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MaintenanceTimingServiceImpl  implements MaintenanceTimingService{
    private  final MaintenanceTimingRepository maintenanceTimingRepository;
    @Override
    public List<MaintenanceTiming> getAll() {
        return maintenanceTimingRepository.findAll();
    }
}
