package com.example.SafeHarborAPI2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ssa_assessments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ssa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ssa_id")
    private Long ssaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", nullable = false)
    private ShipInfo shipInfo;

    @Column(name = "ssa_type")
    private String ssaType;

    @Column(name = "ssa_score")
    private Integer ssaScore;

    @Column(name = "ssa_comments")
    private String ssaComments;

    @Column(name = "is_suspect")
    private Boolean isSuspect = false;

    @CreationTimestamp
    @Column(name = "record_created_at", updatable = false)
    private LocalDateTime recordCreatedAt;
}