package com.xyz.transporte.controller;

import com.xyz.transporte.dto.CamionRequest;
import com.xyz.transporte.dto.CamionResponse;
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

    /*
     * DTO IMPLEMENTADO
     *
     * @RequestBody recibe JSON y Spring lo convierte
     * automáticamente en CamionRequest.
     *
     * DTO:
     * JSON → CamionRequest
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CamionResponse> crear(
            @RequestBody CamionRequest request) {

        /*
         * DTO:
         * CamionRequest → Service
         */
        CamionResponse response =
                camionService.guardar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /*
     * DTO IMPLEMENTADO
     *
     * El Controller devuelve una lista de
     * CamionResponse en lugar de Entity Camion.
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<List<CamionResponse>> listar() {

        return ResponseEntity.ok(
                camionService.listar()
        );
    }

    /*
     * DTO IMPLEMENTADO
     *
     * Entity → CamionResponse → JSON
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERVISOR')")
    public ResponseEntity<CamionResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                camionService.buscarPorId(id)
        );
    }
}