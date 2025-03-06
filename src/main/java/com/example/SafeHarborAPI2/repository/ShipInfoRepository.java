package com.example.SafeHarborAPI2.repository;


import com.example.SafeHarborAPI2.model.ShipInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipInfoRepository extends JpaRepository<ShipInfo, Long> {

    Optional<ShipInfo> findByOfficialRegistryNumber(Long registryNumber);

    List<ShipInfo> findByShipName(String shipName);

    List<ShipInfo> findByShipFlag(String shipFlag);

    List<ShipInfo> findByShipType(String shipType);

    List<ShipInfo> findByShipStatus(String shipStatus);

    List<ShipInfo> findByIsHighRiskCrew(Boolean isHighRiskCrew);

    @Query("SELECT s FROM ShipInfo s WHERE s.cargoCapacityTonnage > ?1")
    List<ShipInfo> findByCargoCapacityGreaterThan(Float capacity);
}