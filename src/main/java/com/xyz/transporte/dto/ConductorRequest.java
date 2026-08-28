package com.xyz.transporte.dto;

/*
 * DTO DE ENTRADA
 *
 * Recibe los datos necesarios para registrar
 * un conductor.
 *
 * No incluimos:
 * - id
 * - estado
 * - camion
 *
 * porque esos datos son manejados por el sistema.
 */
public class ConductorRequest {

    private String nombre;
    private String documento;

    public ConductorRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}