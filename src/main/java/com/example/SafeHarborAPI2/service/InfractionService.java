package com.example.SafeHarborAPI2.service;



import com.example.SafeHarborAPI2.dto.InfractionDTO;
import com.example.SafeHarborAPI2.exception.ResourceNotFoundException;
import com.example.SafeHarborAPI2.model.Infraction;
import com.example.SafeHarborAPI2.model.ShipInfo;
import com.example.SafeHarborAPI2.repository.InfractionRepository;
import com.example.SafeHarborAPI2.repository.ShipInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfractionService {

    private final InfractionRepository infractionRepository;
    private final ShipInfoRepository shipInfoRepository;

    @Autowired
    public InfractionService(InfractionRepository infractionRepository, ShipInfoRepository shipInfoRepository) {
        this.infractionRepository = infractionRepository;
        this.shipInfoRepository = shipInfoRepository;
    }

    public List<InfractionDTO> getAllInfractions() {
        return infractionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public InfractionDTO getInfractionById(Long infractionId) {
        Infraction infraction = infractionRepository.findById(infractionId)
                .orElseThrow(() -> new ResourceNotFoundException("Infraction not found with ID: " + infractionId));
        return convertToDTO(infraction);
    }

    public List<InfractionDTO> getInfractionsByShipId(Long fileId) {
        shipInfoRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + fileId));

        return infractionRepository.findByShipInfo_FileId(fileId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public InfractionDTO createInfraction(InfractionDTO infractionDTO) {
        ShipInfo shipInfo = shipInfoRepository.findById(infractionDTO.getFileId())
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + infractionDTO.getFileId()));

        Infraction infraction = convertToEntity(infractionDTO);
        infraction.setShipInfo(shipInfo);

        Infraction savedInfraction = infractionRepository.save(infraction);
        return convertToDTO(savedInfraction);
    }

    @Transactional
    public InfractionDTO updateInfraction(Long infractionId, InfractionDTO infractionDTO) {
        Infraction existingInfraction = infractionRepository.findById(infractionId)
                .orElseThrow(() -> new ResourceNotFoundException("Infraction not found with ID: " + infractionId));

        // Don't update these fields
        infractionDTO.setInfractionId(infractionId);
        infractionDTO.setRecordCreatedAt(existingInfraction.getRecordCreatedAt());

        // If fileId was changed, check if the new ship exists
        if (!existingInfraction.getShipInfo().getFileId().equals(infractionDTO.getFileId())) {
            ShipInfo newShipInfo = shipInfoRepository.findById(infractionDTO.getFileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + infractionDTO.getFileId()));

            existingInfraction.setShipInfo(newShipInfo);
        }

        // Copy other properties
        existingInfraction.setInfractionType(infractionDTO.getInfractionType());
        existingInfraction.setDetails(infractionDTO.getDetails());
        existingInfraction.setInfractionDate(infractionDTO.getInfractionDate());

        Infraction updatedInfraction = infractionRepository.save(existingInfraction);
        return convertToDTO(updatedInfraction);
    }

    @Transactional
    public void deleteInfraction(Long infractionId) {
        Infraction infraction = infractionRepository.findById(infractionId)
                .orElseThrow(() -> new ResourceNotFoundException("Infraction not found with ID: " + infractionId));

        infractionRepository.delete(infraction);
    }

    public List<InfractionDTO> getInfractionsByType(String type) {
        return infractionRepository.findByInfractionType(type).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<InfractionDTO> getInfractionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return infractionRepository.findByInfractionDateBetween(startDate, endDate).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper methods for DTO conversion
    private InfractionDTO convertToDTO(Infraction infraction) {
        InfractionDTO dto = new InfractionDTO();
        BeanUtils.copyProperties(infraction, dto);
        dto.setFileId(infraction.getShipInfo().getFileId());
        return dto;
    }

    private Infraction convertToEntity(InfractionDTO dto) {
        Infraction entity = new Infraction();
        BeanUtils.copyProperties(dto, entity, "fileId");
        return entity;
    }
}