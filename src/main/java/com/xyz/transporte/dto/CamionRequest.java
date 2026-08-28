package com.xyz.transporte.dto;

/*
 * DTO DE ENTRADA
 *
 * Este DTO recibe los datos enviados por el cliente.
 *
 * El cliente NO necesita enviar el ID porque
 * el ID lo genera automáticamente la base de datos.
 */
public class CamionRequest {

    private String placa;
    private String tipoVehiculo;

    public CamionRequest() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
}