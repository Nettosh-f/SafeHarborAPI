package com.example.SafeHarborAPI2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipInfoDTO {
    private Long fileId;

    @NotBlank(message = "Ship name is required")
    private String shipName;

    private String shipFlag;
    private String shipType;
    private String portOfRegistry;
    private String workingLanguages;

    @NotNull(message = "Official registry number is required")
    private Long officialRegistryNumber;

    private String callSign;
    private String shipOwner;
    private String shipClass;

    @PositiveOrZero(message = "Cargo capacity must be zero or positive")
    private Float cargoCapacityTonnage;

    @PastOrPresent(message = "SSA date cannot be in the future")
    private LocalDate dateOfSsa;

    private String ssaBy;
    private Boolean isHighRiskCrew = false;
    private LocalDateTime recordCreatedAt;
    private LocalDateTime recordUpdatedAt;
    private String shipStatus;
}