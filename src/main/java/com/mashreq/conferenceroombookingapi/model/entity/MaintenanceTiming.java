package com.mashreq.conferenceroombookingapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="maintenance_timing")
public class MaintenanceTiming {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime endTime;
    public MaintenanceTiming(LocalTime startTime,LocalTime endTime){
        this.startTime=startTime;
        this.endTime=endTime;
    }
}
