package com.xyz.transporte.dto;

/*

 * DTO DE RESPUESTA
 *
 * Representa los datos del camión que está
 * asignado a un conductor.
 */
public class CamionAsignadoResponse {

    private Long id;
    private String placa;
    private String tipoVehiculo;

    public CamionAsignadoResponse() {
    }

    public CamionAsignadoResponse(
            Long id,
            String placa,
            String tipoVehiculo) {

        this.id = id;
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;

    }

    public Long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }
}
