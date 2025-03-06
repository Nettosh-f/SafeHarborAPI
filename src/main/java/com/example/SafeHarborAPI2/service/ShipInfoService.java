package com.example.SafeHarborAPI2.service;



import com.example.SafeHarborAPI2.dto.ShipInfoDTO;
import com.example.SafeHarborAPI2.exception.ResourceNotFoundException;
import com.example.SafeHarborAPI2.model.ShipInfo;
import com.example.SafeHarborAPI2.repository.ShipInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipInfoService {

    private final ShipInfoRepository shipInfoRepository;

    @Autowired
    public ShipInfoService(ShipInfoRepository shipInfoRepository) {
        this.shipInfoRepository = shipInfoRepository;
    }

    public List<ShipInfoDTO> getAllShips() {
        return shipInfoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ShipInfoDTO getShipById(Long fileId) {
        ShipInfo shipInfo = shipInfoRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + fileId));
        return convertToDTO(shipInfo);
    }

    public ShipInfoDTO getShipByRegistryNumber(Long registryNumber) {
        ShipInfo shipInfo = shipInfoRepository.findByOfficialRegistryNumber(registryNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with registry number: " + registryNumber));
        return convertToDTO(shipInfo);
    }

    @Transactional
    public ShipInfoDTO createShip(ShipInfoDTO shipInfoDTO) {
        ShipInfo shipInfo = convertToEntity(shipInfoDTO);
        ShipInfo savedShipInfo = shipInfoRepository.save(shipInfo);
        return convertToDTO(savedShipInfo);
    }

    @Transactional
    public ShipInfoDTO updateShip(Long fileId, ShipInfoDTO shipInfoDTO) {
        ShipInfo existingShip = shipInfoRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + fileId));

        // Don't update IDs or timestamps
        shipInfoDTO.setFileId(fileId);
        shipInfoDTO.setRecordCreatedAt(existingShip.getRecordCreatedAt());

        // Copy properties
        BeanUtils.copyProperties(shipInfoDTO, existingShip, "fileId", "recordCreatedAt", "ssaAssessments", "infractions", "harborHistories");

        ShipInfo updatedShip = shipInfoRepository.save(existingShip);
        return convertToDTO(updatedShip);
    }

    @Transactional
    public void deleteShip(Long fileId) {
        ShipInfo shipInfo = shipInfoRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + fileId));

        shipInfoRepository.delete(shipInfo);
    }

    public List<ShipInfoDTO> getShipsByStatus(String status) {
        return shipInfoRepository.findByShipStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ShipInfoDTO> getHighRiskCrewShips() {
        return shipInfoRepository.findByIsHighRiskCrew(true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ShipInfoDTO> getShipsByFlag(String flag) {
        return shipInfoRepository.findByShipFlag(flag).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ShipInfoDTO> getShipsByType(String type) {
        return shipInfoRepository.findByShipType(type).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper methods for DTO conversion
    private ShipInfoDTO convertToDTO(ShipInfo shipInfo) {
        ShipInfoDTO dto = new ShipInfoDTO();
        BeanUtils.copyProperties(shipInfo, dto);
        return dto;
    }

    private ShipInfo convertToEntity(ShipInfoDTO dto) {
        ShipInfo entity = new ShipInfo();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}