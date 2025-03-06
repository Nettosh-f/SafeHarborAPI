package com.example.SafeHarborAPI2.service;


import com.example.SafeHarborAPI2.dto.HarborHistoryDTO;
import com.example.SafeHarborAPI2.exception.ResourceNotFoundException;
import com.example.SafeHarborAPI2.model.HarborHistory;
import com.example.SafeHarborAPI2.model.ShipInfo;
import com.example.SafeHarborAPI2.repository.HarborHistoryRepository;
import com.example.SafeHarborAPI2.repository.ShipInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarborHistoryService {

    private final HarborHistoryRepository harborHistoryRepository;
    private final ShipInfoRepository shipInfoRepository;

    @Autowired
    public HarborHistoryService(HarborHistoryRepository harborHistoryRepository, ShipInfoRepository shipInfoRepository) {
        this.harborHistoryRepository = harborHistoryRepository;
        this.shipInfoRepository = shipInfoRepository;
    }

    public List<HarborHistoryDTO> getAllHistories() {
        return harborHistoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public HarborHistoryDTO getHistoryById(Long historyId) {
        HarborHistory history = harborHistoryRepository.findById(historyId)
                .orElseThrow(() -> new ResourceNotFoundException("Harbor history not found with ID: " + historyId));
        return convertToDTO(history);
    }

    public List<HarborHistoryDTO> getHistoriesByShipId(Long fileId) {
        shipInfoRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + fileId));

        return harborHistoryRepository.findByShipInfo_FileId(fileId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public HarborHistoryDTO createHistory(HarborHistoryDTO historyDTO) {
        ShipInfo shipInfo = shipInfoRepository.findById(historyDTO.getFileId())
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + historyDTO.getFileId()));

        HarborHistory history = convertToEntity(historyDTO);
        history.setShipInfo(shipInfo);

        HarborHistory savedHistory = harborHistoryRepository.save(history);
        return convertToDTO(savedHistory);
    }

    @Transactional
    public HarborHistoryDTO updateHistory(Long historyId, HarborHistoryDTO historyDTO) {
        HarborHistory existingHistory = harborHistoryRepository.findById(historyId)
                .orElseThrow(() -> new ResourceNotFoundException("Harbor history not found with ID: " + historyId));

        // Don't update these fields
        historyDTO.setHistoryId(historyId);
        historyDTO.setRecordCreatedAt(existingHistory.getRecordCreatedAt());

        // If fileId was changed, check if the new ship exists
        if (!existingHistory.getShipInfo().getFileId().equals(historyDTO.getFileId())) {
            ShipInfo newShipInfo = shipInfoRepository.findById(historyDTO.getFileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + historyDTO.getFileId()));

            existingHistory.setShipInfo(newShipInfo);
        }

        // Copy other properties
        existingHistory.setPortVisited(historyDTO.getPortVisited());
        existingHistory.setCountry(historyDTO.getCountry());
        existingHistory.setArrivalDate(historyDTO.getArrivalDate());
        existingHistory.setDepartureDate(historyDTO.getDepartureDate());
        existingHistory.setIsHighRisk(historyDTO.getIsHighRisk());
        existingHistory.setRiskReason(historyDTO.getRiskReason());

        HarborHistory updatedHistory = harborHistoryRepository.save(existingHistory);
        return convertToDTO(updatedHistory);
    }

    @Transactional
    public void deleteHistory(Long historyId) {
        HarborHistory history = harborHistoryRepository.findById(historyId)
                .orElseThrow(() -> new ResourceNotFoundException("Harbor history not found with ID: " + historyId));

        harborHistoryRepository.delete(history);
    }

    public List<HarborHistoryDTO> getHistoriesByPort(String port) {
        return harborHistoryRepository.findByPortVisited(port).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HarborHistoryDTO> getHistoriesByCountry(String country) {
        return harborHistoryRepository.findByCountry(country).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HarborHistoryDTO> getHighRiskHistories() {
        return harborHistoryRepository.findByIsHighRisk(true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HarborHistoryDTO> getCurrentlyDockedShips() {
        return harborHistoryRepository.findByDepartureDateIsNull().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HarborHistoryDTO> getHistoriesByArrivalDateAfter(LocalDate date) {
        return harborHistoryRepository.findByArrivalDateAfter(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper methods for DTO conversion
    private HarborHistoryDTO convertToDTO(HarborHistory history) {
        HarborHistoryDTO dto = new HarborHistoryDTO();
        BeanUtils.copyProperties(history, dto);
        dto.setFileId(history.getShipInfo().getFileId());
        return dto;
    }

    private HarborHistory convertToEntity(HarborHistoryDTO dto) {
        HarborHistory entity = new HarborHistory();
        BeanUtils.copyProperties(dto, entity, "fileId");
        return entity;
    }
}