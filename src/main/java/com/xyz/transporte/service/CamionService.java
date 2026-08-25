package com.xyz.transporte.service;

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

    public Camion guardar(Camion camion) {

        if (camionRepository.findByPlaca(camion.getPlaca()).isPresent()) {
            throw new RuntimeException("Ya existe un camión con esa placa");
        }

        return camionRepository.save(camion);
    }

    public List<Camion> listar() {
        return camionRepository.findAll();
    }

    public Camion buscarPorId(Long id) {

        return camionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Camión no encontrado"));
    }
}