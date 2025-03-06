package com.example.SafeHarborAPI2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ship_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "ship_name", nullable = false)
    private String shipName;

    @Column(name = "ship_flag")
    private String shipFlag;

    @Column(name = "ship_type")
    private String shipType;

    @Column(name = "port_of_registry")
    private String portOfRegistry;

    @Column(name = "working_languages")
    private String workingLanguages;

    @Column(name = "official_registry_number", unique = true)
    private Long officialRegistryNumber;

    @Column(name = "call_sign")
    private String callSign;

    @Column(name = "ship_owner")
    private String shipOwner;

    @Column(name = "class")
    private String shipClass;

    @Column(name = "cargo_capacity_tonnage")
    private Float cargoCapacityTonnage;

    @Column(name = "date_of_ssa")
    private LocalDate dateOfSsa;

    @Column(name = "ssa_by")
    private String ssaBy;

    @Column(name = "is_high_risk_crew")
    private Boolean isHighRiskCrew = false;

    @CreationTimestamp
    @Column(name = "record_created_at", updatable = false)
    private LocalDateTime recordCreatedAt;

    @UpdateTimestamp
    @Column(name = "record_updated_at")
    private LocalDateTime recordUpdatedAt;

    @Column(name = "ship_status")
    private String shipStatus;

    @OneToMany(mappedBy = "shipInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ssa> ssaAssessments = new ArrayList<>();

    @OneToMany(mappedBy = "shipInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Infraction> infractions = new ArrayList<>();

    @OneToMany(mappedBy = "shipInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HarborHistory> harborHistories = new ArrayList<>();
}