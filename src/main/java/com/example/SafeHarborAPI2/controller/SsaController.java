package com.example.SafeHarborAPI2.controller;

import com.example.SafeHarborAPI2.dto.SsaDTO;
import com.example.SafeHarborAPI2.service.SsaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
@Tag(name = "Ship Security Assessment", description = "APIs for managing ship security assessments")
public class SsaController {

    private final SsaService assessmentService;

    @Autowired
    public SsaController(SsaService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping
    @Operation(summary = "Get all assessments", description = "Retrieve a list of all security assessments")
    public ResponseEntity<List<SsaDTO>> getAllAssessments() {
        List<SsaDTO> assessments = assessmentService.getAllAssessments();
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get assessment by ID", description = "Retrieve a specific assessment by its ID")
    public ResponseEntity<SsaDTO> getAssessmentById(@PathVariable("id") Long id) {
        SsaDTO assessment = assessmentService.getAssessmentById(id);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }

    @GetMapping("/ship/{fileId}")
    @Operation(summary = "Get assessments by ship ID", description = "Retrieve all assessments for a specific ship")
    public ResponseEntity<List<SsaDTO>> getAssessmentsByShipId(@PathVariable("fileId") Long fileId) {
        List<SsaDTO> assessments = assessmentService.getAssessmentsByShipId(fileId);
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new assessment", description = "Add a new security assessment")
    public ResponseEntity<SsaDTO> createAssessment(@Valid @RequestBody SsaDTO assessmentDTO) {
        SsaDTO createdAssessment = assessmentService.createAssessment(assessmentDTO);
        return new ResponseEntity<>(createdAssessment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an assessment", description = "Update an existing security assessment")
    public ResponseEntity<SsaDTO> updateAssessment(
            @PathVariable("id") Long id,
            @Valid @RequestBody SsaDTO assessmentDTO) {
        SsaDTO updatedAssessment = assessmentService.updateAssessment(id, assessmentDTO);
        return new ResponseEntity<>(updatedAssessment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an assessment", description = "Remove a security assessment")
    public ResponseEntity<Void> deleteAssessment(@PathVariable("id") Long id) {
        assessmentService.deleteAssessment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Get assessments by type", description = "Retrieve all assessments of a specific type")
    public ResponseEntity<List<SsaDTO>> getAssessmentsByType(@PathVariable("type") String type) {
        List<SsaDTO> assessments = assessmentService.getAssessmentsByType(type);
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    @GetMapping("/suspect")
    @Operation(summary = "Get suspect assessments", description = "Retrieve all assessments marked as suspect")
    public ResponseEntity<List<SsaDTO>> getSuspectAssessments() {
        List<SsaDTO> assessments = assessmentService.getSuspectAssessments();
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    @GetMapping("/score/{minScore}")
    @Operation(summary = "Get assessments by minimum score", description = "Retrieve all assessments with a score greater than or equal to the specified value")
    public ResponseEntity<List<SsaDTO>> getAssessmentsByMinScore(@PathVariable("minScore") Integer minScore) {
        List<SsaDTO> assessments = assessmentService.getAssessmentsByMinScore(minScore);
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }
}