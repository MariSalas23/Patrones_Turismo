package com.turismo.turismo_app.reservas.dominio.exceptions;

public class ReservaNoEncontradaException extends ReservaException {
    public ReservaNoEncontradaException(String id) {
        super("No se encontró la reserva con id: " + id);
    }
}