package com.otten32.core.laboratory.entity;

import com.otten32.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "lab_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabRequest extends BaseEntity {
    
    @Column(name = "patient_id", nullable = false)
    private Long patientId;
    
    @Column(name = "test_type", length = 50)
    private String testType;
    
    @Column(name = "status", length = 20)
    private String status = "PENDING";
    
    @Column(name = "request_date")
    private LocalDateTime requestDate;
    
    @Column(name = "result", columnDefinition = "TEXT")
    private String result;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}
