package com.turismo.turismo_app.reservas.dominio.exceptions;

public class FechaInvalidaReservaException extends ReservaException {
    public FechaInvalidaReservaException() {
        super("La fecha de la reserva no es válida");
    }
}