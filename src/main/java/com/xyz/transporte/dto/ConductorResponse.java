package com.xyz.transporte.dto;

/*
 * DTO DE SALIDA
 *
 * Define la información que nuestra API
 * devolverá sobre un conductor.
 *
 * Utilizamos camionId en lugar de devolver
 * directamente el objeto Camion completo.
 */
public class ConductorResponse {

    private Long id;
    private String nombre;
    private String documento;
    private String estado;
    private Long camionId;

    public ConductorResponse() {
    }

    public ConductorResponse(
            Long id,
            String nombre,
            String documento,
            String estado,
            Long camionId) {

        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.estado = estado;
        this.camionId = camionId;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEstado() {
        return estado;
    }

    public Long getCamionId() {
        return camionId;
    }
}