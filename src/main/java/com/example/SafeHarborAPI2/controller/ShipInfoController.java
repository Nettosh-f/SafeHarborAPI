package com.example.SafeHarborAPI2.controller;


import com.example.SafeHarborAPI2.dto.ShipInfoDTO;
import com.example.SafeHarborAPI2.service.ShipInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ships")
@Tag(name = "Ship Management", description = "APIs for managing ship information")
public class ShipInfoController {

    private final ShipInfoService shipInfoService;

    @Autowired
    public ShipInfoController(ShipInfoService shipInfoService) {
        this.shipInfoService = shipInfoService;
    }

    @GetMapping
    @Operation(summary = "Get all ships", description = "Retrieve a list of all ships in the system")
    public ResponseEntity<List<ShipInfoDTO>> getAllShips() {
        List<ShipInfoDTO> ships = shipInfoService.getAllShips();
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get ship by ID", description = "Retrieve a specific ship by its file ID")
    public ResponseEntity<ShipInfoDTO> getShipById(@PathVariable("id") Long id) {
        ShipInfoDTO ship = shipInfoService.getShipById(id);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @GetMapping("/registry/{registryNumber}")
    @Operation(summary = "Get ship by registry number", description = "Retrieve a specific ship by its official registry number")
    public ResponseEntity<ShipInfoDTO> getShipByRegistryNumber(@PathVariable("registryNumber") Long registryNumber) {
        ShipInfoDTO ship = shipInfoService.getShipByRegistryNumber(registryNumber);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new ship", description = "Add a new ship to the system")
    public ResponseEntity<ShipInfoDTO> createShip(@Valid @RequestBody ShipInfoDTO shipInfoDTO) {
        ShipInfoDTO createdShip = shipInfoService.createShip(shipInfoDTO);
        return new ResponseEntity<>(createdShip, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a ship", description = "Update an existing ship's information")
    public ResponseEntity<ShipInfoDTO> updateShip(
            @PathVariable("id") Long id,
            @Valid @RequestBody ShipInfoDTO shipInfoDTO) {
        ShipInfoDTO updatedShip = shipInfoService.updateShip(id, shipInfoDTO);
        return new ResponseEntity<>(updatedShip, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a ship", description = "Remove a ship from the system")
    public ResponseEntity<Void> deleteShip(@PathVariable("id") Long id) {
        shipInfoService.deleteShip(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get ships by status", description = "Retrieve all ships with a specific status")
    public ResponseEntity<List<ShipInfoDTO>> getShipsByStatus(@PathVariable("status") String status) {
        List<ShipInfoDTO> ships = shipInfoService.getShipsByStatus(status);
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/high-risk-crew")
    @Operation(summary = "Get high risk crew ships", description = "Retrieve all ships marked as having high risk crews")
    public ResponseEntity<List<ShipInfoDTO>> getHighRiskCrewShips() {
        List<ShipInfoDTO> ships = shipInfoService.getHighRiskCrewShips();
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/flag/{flag}")
    @Operation(summary = "Get ships by flag", description = "Retrieve all ships registered under a specific flag")
    public ResponseEntity<List<ShipInfoDTO>> getShipsByFlag(@PathVariable("flag") String flag) {
        List<ShipInfoDTO> ships = shipInfoService.getShipsByFlag(flag);
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Get ships by type", description = "Retrieve all ships of a specific type")
    public ResponseEntity<List<ShipInfoDTO>> getShipsByType(@PathVariable("type") String type) {
        List<ShipInfoDTO> ships = shipInfoService.getShipsByType(type);
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }
}