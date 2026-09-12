package com.otten32.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseDTO {
    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected Long createdBy;
    protected Long updatedBy;
}
