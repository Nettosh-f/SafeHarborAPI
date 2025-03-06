package com.example.SafeHarborAPI2.repository;

import com.example.SafeHarborAPI2.model.Ssa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SsaRepository extends JpaRepository<Ssa, Long> {

    List<Ssa> findByShipInfo_FileId(Long fileId);

    List<Ssa> findBySsaType(String ssaType);

    List<Ssa> findByIsSuspect(Boolean isSuspect);

    @Query("SELECT s FROM Ssa s WHERE s.ssaScore >= ?1")
    List<Ssa> findBySsaScoreGreaterThanEqual(Integer score);

    @Query("SELECT s FROM Ssa s WHERE s.ssaScore <= ?1")
    List<Ssa> findBySsaScoreLessThanEqual(Integer score);

    @Query("SELECT s FROM Ssa s WHERE s.shipInfo.fileId = ?1 AND s.ssaType = ?2")
    List<Ssa> findByFileIdAndSsaType(Long fileId, String ssaType);
}