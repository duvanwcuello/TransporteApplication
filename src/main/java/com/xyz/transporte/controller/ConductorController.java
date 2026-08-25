package com.xyz.transporte.controller;

import com.xyz.transporte.entity.Conductor;
import com.xyz.transporte.service.ConductorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conductores")
public class ConductorController {

    private final ConductorService conductorService;

    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Conductor> crear(
            @RequestBody Conductor conductor) {

        Conductor nuevoConductor =
                conductorService.guardar(conductor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoConductor);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<List<Conductor>> listar() {

        return ResponseEntity.ok(conductorService.listar());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<Conductor> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                conductorService.buscarPorId(id)
        );
    }
}