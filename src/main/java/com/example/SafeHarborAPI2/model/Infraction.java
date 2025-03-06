package com.example.SafeHarborAPI2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "infractions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Infraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "infraction_id")
    private Long infractionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", nullable = false)
    private ShipInfo shipInfo;

    @Column(name = "infraction_type")
    private String infractionType;

    @Column(name = "details")
    private String details;

    @Column(name = "infraction_date")
    private LocalDate infractionDate;

    @CreationTimestamp
    @Column(name = "record_created_at", updatable = false)
    private LocalDateTime recordCreatedAt;
}