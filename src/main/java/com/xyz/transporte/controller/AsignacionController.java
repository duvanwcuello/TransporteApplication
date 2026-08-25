package com.xyz.transporte.controller;

import com.xyz.transporte.entity.Conductor;
import com.xyz.transporte.service.AsignacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asignaciones")
public class AsignacionController {

    private final AsignacionService asignacionService;

    public AsignacionController(AsignacionService asignacionService) {
        this.asignacionService = asignacionService;
    }

    @PutMapping("/conductor/{conductorId}/camion/{camionId}")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public ResponseEntity<Conductor> asignar(
            @PathVariable Long conductorId,
            @PathVariable Long camionId) {

        Conductor conductor =
                asignacionService.asignarConductor(
                        conductorId,
                        camionId
                );

        return ResponseEntity.ok(conductor);
    }
}