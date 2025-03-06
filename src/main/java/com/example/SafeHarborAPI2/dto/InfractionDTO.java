package com.example.SafeHarborAPI2.dto;

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
public class InfractionDTO {
    private Long infractionId;

    @NotNull(message = "Ship file ID is required")
    private Long fileId;

    private String infractionType;
    private String details;

    @PastOrPresent(message = "Infraction date cannot be in the future")
    private LocalDate infractionDate;

    private LocalDateTime recordCreatedAt;
}