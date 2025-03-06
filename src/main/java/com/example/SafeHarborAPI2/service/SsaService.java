package com.example.SafeHarborAPI2.service;

import com.example.SafeHarborAPI2.dto.SsaDTO;
import com.example.SafeHarborAPI2.exception.ResourceNotFoundException;
import com.example.SafeHarborAPI2.model.ShipInfo;
import com.example.SafeHarborAPI2.model.Ssa;
import com.example.SafeHarborAPI2.repository.ShipInfoRepository;
import com.example.SafeHarborAPI2.repository.SsaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SsaService {

    private final SsaRepository ssaRepository;
    private final ShipInfoRepository shipInfoRepository;

    @Autowired
    public SsaService(SsaRepository ssaRepository, ShipInfoRepository shipInfoRepository) {
        this.ssaRepository = ssaRepository;
        this.shipInfoRepository = shipInfoRepository;
    }

    public List<SsaDTO> getAllAssessments() {
        return ssaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SsaDTO getAssessmentById(Long ssaId) {
        Ssa assessment = ssaRepository.findById(ssaId)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment not found with ID: " + ssaId));
        return convertToDTO(assessment);
    }

    public List<SsaDTO> getAssessmentsByShipId(Long fileId) {
        shipInfoRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + fileId));

        return ssaRepository.findByShipInfo_FileId(fileId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public SsaDTO createAssessment(SsaDTO assessmentDTO) {
        ShipInfo shipInfo = shipInfoRepository.findById(assessmentDTO.getFileId())
                .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + assessmentDTO.getFileId()));

        Ssa assessment = convertToEntity(assessmentDTO);
        assessment.setShipInfo(shipInfo);

        Ssa savedAssessment = ssaRepository.save(assessment);
        return convertToDTO(savedAssessment);
    }

    @Transactional
    public SsaDTO updateAssessment(Long ssaId, SsaDTO assessmentDTO) {
        Ssa existingAssessment = ssaRepository.findById(ssaId)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment not found with ID: " + ssaId));

        // Don't update these fields
        assessmentDTO.setSsaId(ssaId);
        assessmentDTO.setRecordCreatedAt(existingAssessment.getRecordCreatedAt());

        // If fileId was changed, check if the new ship exists
        if (!existingAssessment.getShipInfo().getFileId().equals(assessmentDTO.getFileId())) {
            ShipInfo newShipInfo = shipInfoRepository.findById(assessmentDTO.getFileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ship not found with ID: " + assessmentDTO.getFileId()));

            existingAssessment.setShipInfo(newShipInfo);
        }

        // Copy other properties
        existingAssessment.setSsaType(assessmentDTO.getSsaType());
        existingAssessment.setSsaScore(assessmentDTO.getSsaScore());
        existingAssessment.setSsaComments(assessmentDTO.getSsaComments());
        existingAssessment.setIsSuspect(assessmentDTO.getIsSuspect());

        Ssa updatedAssessment = ssaRepository.save(existingAssessment);
        return convertToDTO(updatedAssessment);
    }

    @Transactional
    public void deleteAssessment(Long ssaId) {
        Ssa assessment = ssaRepository.findById(ssaId)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment not found with ID: " + ssaId));

        ssaRepository.delete(assessment);
    }

    public List<SsaDTO> getAssessmentsByType(String type) {
        return ssaRepository.findBySsaType(type).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SsaDTO> getSuspectAssessments() {
        return ssaRepository.findByIsSuspect(true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SsaDTO> getAssessmentsByMinScore(Integer minScore) {
        return ssaRepository.findBySsaScoreGreaterThanEqual(minScore).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper methods for DTO conversion
    private SsaDTO convertToDTO(Ssa assessment) {
        SsaDTO dto = new SsaDTO();
        BeanUtils.copyProperties(assessment, dto);
        dto.setFileId(assessment.getShipInfo().getFileId());
        return dto;
    }

    private Ssa convertToEntity(SsaDTO dto) {
        Ssa entity = new Ssa();
        BeanUtils.copyProperties(dto, entity, "fileId");
        return entity;
    }
}