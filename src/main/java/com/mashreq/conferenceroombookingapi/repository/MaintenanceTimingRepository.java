package com.mashreq.conferenceroombookingapi.repository;

import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTiming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceTimingRepository extends JpaRepository<MaintenanceTiming,Long> {
}
