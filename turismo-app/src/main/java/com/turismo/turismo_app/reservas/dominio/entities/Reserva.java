package com.turismo.turismo_app.reservas.dominio.entities;

import java.time.LocalDateTime;

public class Reserva {

    private String id;
    private String usuarioId;
    private String tourId;
    private int cantidadPersonas;
    private LocalDateTime fechaReserva;
    private EstadoReserva estado;

    public Reserva(String id, String usuarioId, String tourId, int cantidadPersonas, LocalDateTime fechaReserva) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.tourId = tourId;
        this.cantidadPersonas = cantidadPersonas;
        this.fechaReserva = fechaReserva;
        this.estado = EstadoReserva.PENDIENTE;
    }

    public void confirmar() {
        this.estado = EstadoReserva.CONFIRMADA;
    }

    public void cancelar() {
        this.estado = EstadoReserva.CANCELADA;
    }

    public String getId() { return id; }
    public String getUsuarioId() { return usuarioId; }
    public String getTourId() { return tourId; }
    public int getCantidadPersonas() { return cantidadPersonas; }
    public LocalDateTime getFechaReserva() { return fechaReserva; }
    public EstadoReserva getEstado() { return estado; }

    public void setId(String id) { this.id = id; }
}