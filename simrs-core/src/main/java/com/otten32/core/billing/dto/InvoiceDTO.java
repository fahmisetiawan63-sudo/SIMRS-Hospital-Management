package com.otten32.core.billing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.otten32.common.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDTO extends BaseDTO {
    private Long patientId;
    private String invoiceNo;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private String status;
    private LocalDateTime invoiceDate;
    private LocalDate dueDate;
    private String notes;
}
