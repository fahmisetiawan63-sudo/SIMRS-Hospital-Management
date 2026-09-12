package com.otten32.core.pharmacy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.otten32.common.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicineDTO extends BaseDTO {
    private String code;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private Integer quantity;
    private LocalDate expiryDate;
    private Long supplierId;
    private String status;
}
