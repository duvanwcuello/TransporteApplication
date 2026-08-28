package com.xyz.transporte.service;

import com.xyz.transporte.dto.CamionAsignadoResponse;
import com.xyz.transporte.dto.ConductorResponse;
import com.xyz.transporte.entity.Camion;
import com.xyz.transporte.entity.Conductor;
import com.xyz.transporte.repository.CamionRepository;
import com.xyz.transporte.repository.ConductorRepository;
import org.springframework.stereotype.Service;

@Service
public class AsignacionService {

    private final ConductorRepository conductorRepository;

    private final CamionRepository camionRepository;

    public AsignacionService(
            ConductorRepository conductorRepository,
            CamionRepository camionRepository) {

        this.conductorRepository = conductorRepository;
        this.camionRepository = camionRepository;
    }

    /*
     * DTO IMPLEMENTADO
     *
     * Asigna un camión a un conductor
     * y devuelve la información mediante DTO.
     */
    public ConductorResponse asignarConductor(
            Long conductorId,
            Long camionId) {

        Conductor conductor =
                conductorRepository.findById(conductorId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Conductor no encontrado"
                                )
                        );

        Camion camion =
                camionRepository.findById(camionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Camión no encontrado"
                                )
                        );

        /*
         * Realizamos la asociación.
         */
        conductor.setCamion(camion);

        /*
         * Guardamos el cambio.
         */
        Conductor actualizado =
                conductorRepository.save(conductor);

        /*
         * DTO IMPLEMENTADO
         *
         * Entity Camion → CamionAsignadoResponse
         */
        CamionAsignadoResponse asignacion =
                new CamionAsignadoResponse(
                        camion.getId(),
                        camion.getPlaca(),
                        camion.getTipoVehiculo()
                );

        /*
         * DTO IMPLEMENTADO
         *
         * Entity Conductor → ConductorResponse
         */
        return new ConductorResponse(
                actualizado.getId(),
                actualizado.getNombre(),
                actualizado.getDocumento(),
                actualizado.getEstado(),
                asignacion
        );
    }
}