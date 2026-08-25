package com.xyz.transporte.repository;

import com.xyz.transporte.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {

    Optional<Conductor> findByDocumento(String documento);
}