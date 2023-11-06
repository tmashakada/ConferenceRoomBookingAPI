package com.mashreq.conferenceroombookingapi.service;

import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTime;
import com.mashreq.conferenceroombookingapi.repository.MaintenanceTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MaintenanceTimeServiceImpl implements MaintenanceTimeService {
    private  final MaintenanceTimeRepository maintenanceTimeRepository;
    @Override
    public List<MaintenanceTime> getAll() {
        return maintenanceTimeRepository.findAll();
    }
}
