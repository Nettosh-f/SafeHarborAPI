package com.example.SafeHarborAPI2.repository;

import com.example.SafeHarborAPI2.model.Infraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InfractionRepository extends JpaRepository<Infraction, Long> {

    List<Infraction> findByShipInfo_FileId(Long fileId);

    List<Infraction> findByInfractionType(String infractionType);

    @Query("SELECT i FROM Infraction i WHERE i.infractionDate >= ?1")
    List<Infraction> findByInfractionDateAfter(LocalDate date);

    @Query("SELECT i FROM Infraction i WHERE i.infractionDate <= ?1")
    List<Infraction> findByInfractionDateBefore(LocalDate date);

    @Query("SELECT i FROM Infraction i WHERE i.infractionDate BETWEEN ?1 AND ?2")
    List<Infraction> findByInfractionDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT i FROM Infraction i WHERE i.shipInfo.fileId = ?1 AND i.infractionType = ?2")
    List<Infraction> findByFileIdAndInfractionType(Long fileId, String infractionType);
}