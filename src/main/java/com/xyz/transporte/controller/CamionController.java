package com.xyz.transporte.controller;

import com.xyz.transporte.entity.Camion;
import com.xyz.transporte.service.CamionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camiones")
public class CamionController {

    private final CamionService camionService;

    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Camion> crear(@RequestBody Camion camion) {

        Camion nuevoCamion = camionService.guardar(camion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoCamion);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<List<Camion>> listar() {

        return ResponseEntity.ok(camionService.listar());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<Camion> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(camionService.buscarPorId(id));
    }
}
