package com.co.shopeasy.logistica.service;


import com.co.shopeasy.logistica.entity.Camion;
import com.co.shopeasy.logistica.repository.CamionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CamionService {

    private final CamionRepository camionRepository;

    public CamionService(CamionRepository camionRepository) {
        this.camionRepository = camionRepository;
    }

    //registrar un Camion

    public Camion crarCamion(Camion camion) {

        if (camion == null){
            throw new IllegalArgumentException(
                    "Camion no puede ser null"
            );
        }

        if(camion.getPlaca()==null || camion.getPlaca().trim().isEmpty()){
            throw new IllegalArgumentException(
                    "La placa es Obligatoria."
            );
        }

        String placa = camion.getPlaca().trim().toUpperCase();

        if (camionRepository.existsByPlaca(placa)) {
            throw new IllegalArgumentException(
                "La placa ya esta registrado: " + placa
            );
        }

        camion.setPlaca(placa);

        return camionRepository.save(camion);
    }

    //listar camiones
    public List<Camion> ListarCamiones() {
        return camionRepository.findAll();
    }

    //Buscar un Camion
    public Camion buscarPorId(Long id) {
        if(id==null){
            throw new IllegalArgumentException(
                    "El id no puede ser null"
            );
        }

        return camionRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "No se encontro el camion con el ID: " + id
                )
        );
    }

    //Buscar un camion por Placa.
    public Camion buscarPorPlaca(String placa){

        if(placa==null || placa.trim().isEmpty()){
            throw new IllegalArgumentException(
                    "La placa es Obligatoria."
            );
        }
        String placaNormalizada = placa.trim().toUpperCase();

        return camionRepository.findByplaca(placaNormalizada)
            .orElseThrow(() -> new RuntimeException(
                    "No se encontro el camion con placa: " + placaNormalizada
            )
        );
    }

    //actualizar un camion
    public Camion actualizarCamion(Long id, Camion  datosActializados) {

        if (datosActializados == null) {
            throw new IllegalArgumentException(
                    "Los datos del camion son obligatorios"
            );
        }
        Camion camionExistente = buscarPorId(id);

        if (datosActializados.getPlaca() == null ||
                datosActializados.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La placa es Obligatoria."
            );
        }

        String nuevaPlaca =
                datosActializados.getPlaca()
                        .trim().
                        toUpperCase();

        //Verificar nueva placa
        Optional<Camion> camionConMismaPlaca =
                camionRepository.findByplaca(nuevaPlaca);
        if (camionConMismaPlaca.isPresent() && !camionConMismaPlaca.get()
                .getId().equals(id)) {
            throw new IllegalArgumentException(
                    "la placa " + nuevaPlaca
                            + " ya existe en el sistema."
            );
        }

        camionExistente.setPlaca(nuevaPlaca);
        camionExistente.setMarca(datosActializados.getMarca());
        camionExistente.setModelo(datosActializados.getModelo());
        camionExistente.setTipo(datosActializados.getTipo());
        camionExistente.setCapacidad(
                datosActializados.getCapacidad()
        );
        camionExistente.setEstado(datosActializados.getEstado());


    }

}
