package com.xyz.transporte.service;

import com.xyz.transporte.dto.CamionAsignadoResponse;
import com.xyz.transporte.dto.ConductorRequest;
import com.xyz.transporte.dto.ConductorResponse;
import com.xyz.transporte.entity.Camion;
import com.xyz.transporte.entity.Conductor;
import com.xyz.transporte.repository.ConductorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConductorService {

    private final ConductorRepository conductorRepository;

    public ConductorService(ConductorRepository conductorRepository) {

        this.conductorRepository = conductorRepository;
    }

    /*
     * DTO IMPLEMENTADO
     *
     * Recibimos ConductorRequest.
     */
    public ConductorResponse guardar(
            ConductorRequest request) {

        if (conductorRepository
                .findByDocumento(request.getDocumento())
                .isPresent()) {

            throw new RuntimeException(
                    "Ya existe un conductor con ese documento"
            );
        }

        /*
         * Convertimos:
         *
         * ConductorRequest → Conductor
         */
        Conductor conductor = new Conductor();

        conductor.setNombre(request.getNombre());
        conductor.setDocumento(request.getDocumento());

        /*
         * El estado lo controla el sistema.
         */
        conductor.setEstado("ACTIVO");

        Conductor guardado =
                conductorRepository.save(conductor);

        /*
         * Convertimos:
         *
         * Conductor → ConductorResponse
         */
        return convertirAResponse(guardado);
    }

    /*
     * DTO IMPLEMENTADO
     *
     * Devolvemos DTOs y no Entities.
     */
    public List<ConductorResponse> listar() {

        return conductorRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    /*
     * DTO IMPLEMENTADO
     */
    public ConductorResponse buscarPorId(Long id) {

        Conductor conductor =
                conductorRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Conductor no encontrado"
                                )
                        );

        return convertirAResponse(conductor);
    }

    /*
     * DTO IMPLEMENTADO
     *
     * Convierte Entity → DTO.
     */
    private ConductorResponse convertirAResponse(
            Conductor conductor) {

        CamionAsignadoResponse asignacion = null;

        if (conductor.getCamion() != null) {
            Camion camion = conductor.getCamion();

            /*
             * Convertimos la Entity Camion
             * en CamionAsignadoResponse.
             */

            asignacion = new CamionAsignadoResponse(
                    camion.getId(),
                    camion.getPlaca(),
                    camion.getTipoVehiculo()

                    );
        }

        return new ConductorResponse(
                conductor.getId(),
                conductor.getNombre(),
                conductor.getDocumento(),
                conductor.getEstado(),
                asignacion
        );
    }
}