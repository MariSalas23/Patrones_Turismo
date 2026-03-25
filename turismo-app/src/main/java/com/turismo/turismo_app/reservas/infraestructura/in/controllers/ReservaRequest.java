package com.turismo.turismo_app.reservas.infraestructura.in.controllers;

import java.time.LocalDateTime;

public class ReservaRequest {

    private String usuarioId;
    private String tourId;
    private int cantidadPersonas;
    private LocalDateTime fecha;

    public String getUsuarioId() { return usuarioId; }
    public String getTourId() { return tourId; }
    public int getCantidadPersonas() { return cantidadPersonas; }
    public LocalDateTime getFecha() { return fecha; }
}