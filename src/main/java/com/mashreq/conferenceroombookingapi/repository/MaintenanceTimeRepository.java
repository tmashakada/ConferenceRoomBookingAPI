package com.mashreq.conferenceroombookingapi.repository;

import com.mashreq.conferenceroombookingapi.model.entity.MaintenanceTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceTimeRepository extends JpaRepository<MaintenanceTime,Long> {
}
