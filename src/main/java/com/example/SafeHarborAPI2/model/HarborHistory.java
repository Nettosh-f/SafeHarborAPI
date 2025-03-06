package com.example.SafeHarborAPI2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "harbor_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HarborHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", nullable = false)
    private ShipInfo shipInfo;

    @Column(name = "port_visited", nullable = false)
    private String portVisited;

    @Column(name = "country")
    private String country;

    @Column(name = "arrival_date", nullable = false)
    private LocalDate arrivalDate;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "is_high_risk")
    private Boolean isHighRisk = false;

    @Column(name = "risk_reason")
    private String riskReason;

    @CreationTimestamp
    @Column(name = "record_created_at", updatable = false)
    private LocalDateTime recordCreatedAt;
}