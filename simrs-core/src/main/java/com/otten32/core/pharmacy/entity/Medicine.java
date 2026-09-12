package com.otten32.core.pharmacy.entity;

import com.otten32.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "medicines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine extends BaseEntity {
    
    @Column(name = "code", unique = true, nullable = false, length = 20)
    private String code;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;
    
    @Column(name = "quantity")
    private Integer quantity = 0;
    
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    
    @Column(name = "supplier_id")
    private Long supplierId;
    
    @Column(name = "status", length = 20)
    private String status = "ACTIVE";
}
