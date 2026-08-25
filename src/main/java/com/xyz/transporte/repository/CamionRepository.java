package com.xyz.transporte.repository;

import com.xyz.transporte.entity.Camion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CamionRepository extends JpaRepository<Camion, Long> {

    Optional<Camion> findByPlaca(String placa);
}