package com.xyz.transporte.service;

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

    public Conductor asignarConductor(Long conductorId, Long camionId) {

        Conductor conductor = conductorRepository.findById(conductorId)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));

        Camion camion = camionRepository.findById(camionId)
                .orElseThrow(() -> new RuntimeException("Camión no encontrado"));

        conductor.setCamion(camion);

        return conductorRepository.save(conductor);
    }
}