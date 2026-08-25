package com.xyz.transporte.service;

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

    public Conductor guardar(Conductor conductor) {

        if (conductorRepository.findByDocumento(conductor.getDocumento()).isPresent()) {
            throw new RuntimeException("Ya existe un conductor con ese documento");
        }

        conductor.setEstado("ACTIVO");

        return conductorRepository.save(conductor);
    }

    public List<Conductor> listar() {
        return conductorRepository.findAll();
    }

    public Conductor buscarPorId(Long id) {

        return conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
    }
}