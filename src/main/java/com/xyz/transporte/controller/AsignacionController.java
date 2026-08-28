package com.xyz.transporte.controller;

import com.xyz.transporte.dto.ConductorResponse;
import com.xyz.transporte.service.AsignacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asignaciones")
public class AsignacionController {

    private final AsignacionService asignacionService;

    public AsignacionController(
            AsignacionService asignacionService) {

        this.asignacionService = asignacionService;
    }

    /*
     * DTO IMPLEMENTADO
     *
     * Los IDs llegan mediante @PathVariable.
     *
     * Ejemplo:
     *
     * /conductor/1/camion/2
     *
     * El Service realiza la operación y
     * devuelve un ConductorResponse.
     */
    @PutMapping("/conductor/{conductorId}/camion/{camionId}")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public ResponseEntity<ConductorResponse> asignar(
            @PathVariable Long conductorId,
            @PathVariable Long camionId) {

        /*
         * DTO:
         *
         * Service → ConductorResponse
         */
        ConductorResponse response =
                asignacionService.asignarConductor(
                        conductorId,
                        camionId
                );

        return ResponseEntity.ok(response);
    }
}