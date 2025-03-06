package com.example.SafeHarborAPI2.repository;

import com.example.SafeHarborAPI2.model.HarborHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HarborHistoryRepository extends JpaRepository<HarborHistory, Long> {

    List<HarborHistory> findByShipInfo_FileId(Long fileId);

    List<HarborHistory> findByPortVisited(String portVisited);

    List<HarborHistory> findByCountry(String country);

    List<HarborHistory> findByIsHighRisk(Boolean isHighRisk);

    List<HarborHistory> findByRiskReason(String riskReason);

    @Query("SELECT h FROM HarborHistory h WHERE h.arrivalDate >= ?1")
    List<HarborHistory> findByArrivalDateAfter(LocalDate date);

    @Query("SELECT h FROM HarborHistory h WHERE h.arrivalDate <= ?1")
    List<HarborHistory> findByArrivalDateBefore(LocalDate date);

    @Query("SELECT h FROM HarborHistory h WHERE h.departureDate IS NULL")
    List<HarborHistory> findByDepartureDateIsNull();

    @Query("SELECT h FROM HarborHistory h WHERE h.shipInfo.fileId = ?1 AND h.isHighRisk = true")
    List<HarborHistory> findHighRiskHistoryByFileId(Long fileId);
}