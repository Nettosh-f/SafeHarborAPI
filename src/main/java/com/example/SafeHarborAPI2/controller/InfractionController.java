package com.example.SafeHarborAPI2.controller;


import com.example.SafeHarborAPI2.dto.InfractionDTO;
import com.example.SafeHarborAPI2.service.InfractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/infractions")
@Tag(name = "Ship Infractions", description = "APIs for managing ship infractions")
public class InfractionController {

    private final InfractionService infractionService;

    @Autowired
    public InfractionController(InfractionService infractionService) {
        this.infractionService = infractionService;
    }

    @GetMapping
    @Operation(summary = "Get all infractions", description = "Retrieve a list of all infractions")
    public ResponseEntity<List<InfractionDTO>> getAllInfractions() {
        List<InfractionDTO> infractions = infractionService.getAllInfractions();
        return new ResponseEntity<>(infractions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get infraction by ID", description = "Retrieve a specific infraction by its ID")
    public ResponseEntity<InfractionDTO> getInfractionById(@PathVariable("id") Long id) {
        InfractionDTO infraction = infractionService.getInfractionById(id);
        return new ResponseEntity<>(infraction, HttpStatus.OK);
    }

    @GetMapping("/ship/{fileId}")
    @Operation(summary = "Get infractions by ship ID", description = "Retrieve all infractions for a specific ship")
    public ResponseEntity<List<InfractionDTO>> getInfractionsByShipId(@PathVariable("fileId") Long fileId) {
        List<InfractionDTO> infractions = infractionService.getInfractionsByShipId(fileId);
        return new ResponseEntity<>(infractions, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new infraction", description = "Add a new infraction record")
    public ResponseEntity<InfractionDTO> createInfraction(@Valid @RequestBody InfractionDTO infractionDTO) {
        InfractionDTO createdInfraction = infractionService.createInfraction(infractionDTO);
        return new ResponseEntity<>(createdInfraction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an infraction", description = "Update an existing infraction record")
    public ResponseEntity<InfractionDTO> updateInfraction(
            @PathVariable("id") Long id,
            @Valid @RequestBody InfractionDTO infractionDTO) {
        InfractionDTO updatedInfraction = infractionService.updateInfraction(id, infractionDTO);
        return new ResponseEntity<>(updatedInfraction, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an infraction", description = "Remove an infraction record")
    public ResponseEntity<Void> deleteInfraction(@PathVariable("id") Long id) {
        infractionService.deleteInfraction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Get infractions by type", description = "Retrieve all infractions of a specific type")
    public ResponseEntity<List<InfractionDTO>> getInfractionsByType(@PathVariable("type") String type) {
        List<InfractionDTO> infractions = infractionService.getInfractionsByType(type);
        return new ResponseEntity<>(infractions, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get infractions by date range", description = "Retrieve all infractions within a specified date range")
    public ResponseEntity<List<InfractionDTO>> getInfractionsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<InfractionDTO> infractions = infractionService.getInfractionsByDateRange(startDate, endDate);
        return new ResponseEntity<>(infractions, HttpStatus.OK);
    }
}