package com.xyz.transporte.dto;

/*
 * DTO DE SALIDA
 *
 * Este DTO define exactamente los datos que
 * queremos devolver al cliente.
 *
 * De esta manera no exponemos directamente
 * la Entity Camion.
 */
public class CamionResponse {

    private Long id;
    private String placa;
    private String tipoVehiculo;

    public CamionResponse() {
    }

    public CamionResponse(
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