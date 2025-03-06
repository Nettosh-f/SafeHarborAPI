package com.example.SafeHarborAPI2.controller;

import com.example.SafeHarborAPI2.dto.HarborHistoryDTO;
import com.example.SafeHarborAPI2.service.HarborHistoryService;
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
@RequestMapping("/api/harbor-history")
@Tag(name = "Harbor History", description = "APIs for managing ship harbor visit history")
public class HarborHistoryController {

    private final HarborHistoryService harborHistoryService;

    @Autowired
    public HarborHistoryController(HarborHistoryService harborHistoryService) {
        this.harborHistoryService = harborHistoryService;
    }

    @GetMapping
    @Operation(summary = "Get all history records", description = "Retrieve a list of all harbor visit history records")
    public ResponseEntity<List<HarborHistoryDTO>> getAllHistories() {
        List<HarborHistoryDTO> histories = harborHistoryService.getAllHistories();
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get history by ID", description = "Retrieve a specific harbor visit history record by its ID")
    public ResponseEntity<HarborHistoryDTO> getHistoryById(@PathVariable("id") Long id) {
        HarborHistoryDTO history = harborHistoryService.getHistoryById(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/ship/{fileId}")
    @Operation(summary = "Get history by ship ID", description = "Retrieve all harbor visit history records for a specific ship")
    public ResponseEntity<List<HarborHistoryDTO>> getHistoriesByShipId(@PathVariable("fileId") Long fileId) {
        List<HarborHistoryDTO> histories = harborHistoryService.getHistoriesByShipId(fileId);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new history record", description = "Add a new harbor visit history record")
    public ResponseEntity<HarborHistoryDTO> createHistory(@Valid @RequestBody HarborHistoryDTO historyDTO) {
        HarborHistoryDTO createdHistory = harborHistoryService.createHistory(historyDTO);
        return new ResponseEntity<>(createdHistory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a history record", description = "Update an existing harbor visit history record")
    public ResponseEntity<HarborHistoryDTO> updateHistory(
            @PathVariable("id") Long id,
            @Valid @RequestBody HarborHistoryDTO historyDTO) {
        HarborHistoryDTO updatedHistory = harborHistoryService.updateHistory(id, historyDTO);
        return new ResponseEntity<>(updatedHistory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a history record", description = "Remove a harbor visit history record")
    public ResponseEntity<Void> deleteHistory(@PathVariable("id") Long id) {
        harborHistoryService.deleteHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/port/{port}")
    @Operation(summary = "Get histories by port", description = "Retrieve all history records for a specific port")
    public ResponseEntity<List<HarborHistoryDTO>> getHistoriesByPort(@PathVariable("port") String port) {
        List<HarborHistoryDTO> histories = harborHistoryService.getHistoriesByPort(port);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/country/{country}")
    @Operation(summary = "Get histories by country", description = "Retrieve all history records for a specific country")
    public ResponseEntity<List<HarborHistoryDTO>> getHistoriesByCountry(@PathVariable("country") String country) {
        List<HarborHistoryDTO> histories = harborHistoryService.getHistoriesByCountry(country);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/high-risk")
    @Operation(summary = "Get high risk histories", description = "Retrieve all history records marked as high risk")
    public ResponseEntity<List<HarborHistoryDTO>> getHighRiskHistories() {
        List<HarborHistoryDTO> histories = harborHistoryService.getHighRiskHistories();
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/currently-docked")
    @Operation(summary = "Get currently docked ships", description = "Retrieve all history records where ships are currently docked (no departure date)")
    public ResponseEntity<List<HarborHistoryDTO>> getCurrentlyDockedShips() {
        List<HarborHistoryDTO> histories = harborHistoryService.getCurrentlyDockedShips();
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/arrived-after")
    @Operation(summary = "Get histories after date", description = "Retrieve all history records with arrival after specified date")
    public ResponseEntity<List<HarborHistoryDTO>> getHistoriesAfterDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<HarborHistoryDTO> histories = harborHistoryService.getHistoriesByArrivalDateAfter(date);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
}