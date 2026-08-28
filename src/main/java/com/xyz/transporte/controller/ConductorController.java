package com.xyz.transporte.controller;

import com.xyz.transporte.dto.ConductorRequest;
import com.xyz.transporte.dto.ConductorResponse;
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

    public ConductorController(
            ConductorService conductorService) {

        this.conductorService = conductorService;
    }

    /*
     * DTO IMPLEMENTADO
     *
     * JSON recibido desde Postman:
     *
     * {
     *     "nombre": "Carlos Perez",
     *     "documento": "1234567890"
     * }
     *
     * se convierte automáticamente en:
     *
     * ConductorRequest
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ConductorResponse> crear(
            @RequestBody ConductorRequest request) {

        /*
         * DTO:
         *
         * ConductorRequest → Service
         */
        ConductorResponse response =
                conductorService.guardar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /*
     * DTO IMPLEMENTADO
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<List<ConductorResponse>> listar() {

        return ResponseEntity.ok(
                conductorService.listar()
        );
    }

    /*
     * DTO IMPLEMENTADO
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<ConductorResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                conductorService.buscarPorId(id)
        );
    }
}