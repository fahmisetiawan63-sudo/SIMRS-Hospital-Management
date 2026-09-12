package com.otten32.core.radiology.entity;

import com.otten32.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "radiology_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadiologyRequest extends BaseEntity {
    
    @Column(name = "patient_id", nullable = false)
    private Long patientId;
    
    @Column(name = "examination_type", length = 50)
    private String examinationType;
    
    @Column(name = "status", length = 20)
    private String status = "PENDING";
    
    @Column(name = "request_date")
    private LocalDateTime requestDate;
    
    @Column(name = "image_path", length = 255)
    private String imagePath;
    
    @Column(name = "findings", columnDefinition = "TEXT")
    private String findings;
    
    @Column(name = "verified_by")
    private Long verifiedBy;
    
    @Column(name = "verified_date")
    private LocalDateTime verifiedDate;
}
