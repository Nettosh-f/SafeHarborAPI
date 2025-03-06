package com.example.SafeHarborAPI2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HarborHistoryDTO {
    private Long historyId;

    @NotNull(message = "Ship file ID is required")
    private Long fileId;

    @NotBlank(message = "Port visited is required")
    private String portVisited;

    private String country;

    @NotNull(message = "Arrival date is required")
    @PastOrPresent(message = "Arrival date cannot be in the future")
    private LocalDate arrivalDate;

    @PastOrPresent(message = "Departure date cannot be in the future")
    private LocalDate departureDate;

    private Boolean isHighRisk = false;
    private String riskReason;
    private LocalDateTime recordCreatedAt;
}