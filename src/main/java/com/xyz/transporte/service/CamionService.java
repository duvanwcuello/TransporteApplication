package com.xyz.transporte.service;

import com.xyz.transporte.dto.CamionRequest;
import com.xyz.transporte.dto.CamionResponse;
import com.xyz.transporte.entity.Camion;
import com.xyz.transporte.repository.CamionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CamionService {

    private final CamionRepository camionRepository;

    public CamionService(CamionRepository camionRepository) {
        this.camionRepository = camionRepository;
    }

    /*
     * DTO IMPLEMENTADO
     *
     * Recibimos CamionRequest en lugar de recibir
     * directamente la Entity Camion.
     */
    public CamionResponse guardar(CamionRequest request) {

        if (camionRepository
                .findByPlaca(request.getPlaca())
                .isPresent()) {

            throw new RuntimeException(
                    "Ya existe un camión con esa placa"
            );
        }

        /*
         * Convertimos el DTO de entrada
         * en una Entity.
         */
        Camion camion = new Camion();

        camion.setPlaca(request.getPlaca());
        camion.setTipoVehiculo(request.getTipoVehiculo());

        Camion guardado = camionRepository.save(camion);

        /*
         * Convertimos la Entity guardada
         * en un DTO de respuesta.
         */
        return convertirAResponse(guardado);
    }

    /*
     * DTO IMPLEMENTADO
     *
     * El método devuelve una lista de DTOs
     * y no una lista de Entities.
     */
    public List<CamionResponse> listar() {

        return camionRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    /*
     * DTO IMPLEMENTADO
     *
     * Buscamos una Entity pero devolvemos
     * un CamionResponse.
     */
    public CamionResponse buscarPorId(Long id) {

        Camion camion = camionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Camión no encontrado"
                        )
                );

        return convertirAResponse(camion);
    }

    /*
     * MÉTODO DE CONVERSIÓN
     *
     * Entity → DTO
     */
    private CamionResponse convertirAResponse(Camion camion) {

        return new CamionResponse(
                camion.getId(),
                camion.getPlaca(),
                camion.getTipoVehiculo()
        );
    }
}