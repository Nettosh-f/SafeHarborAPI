package com.example.SafeHarborAPI2.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SsaDTO {
    private Long ssaId;

    @NotNull(message = "Ship file ID is required")
    private Long fileId;

    private String ssaType;

    @Min(value = 0, message = "Score must be at least 0")
    @Max(value = 100, message = "Score must be at most 100")
    private Integer ssaScore;

    private String ssaComments;
    private Boolean isSuspect = false;
    private LocalDateTime recordCreatedAt;
}