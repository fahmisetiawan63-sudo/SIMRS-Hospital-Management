package com.otten32.core.pharmacy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMedicineRequest {
    
    @NotBlank(message = "Medicine code is required")
    private String code;
    
    @NotBlank(message = "Medicine name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Unit price is required")
    @Min(value = 0, message = "Unit price must be greater than 0")
    private BigDecimal unitPrice;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;
    
    private LocalDate expiryDate;
    
    private Long supplierId;
}
