package com.turismo.turismo_app.tours.dominio.exceptions;

public class FechaInvalidaException extends TourException {
    public FechaInvalidaException(String fecha) {
        super("La fecha no puede estar en el pasado", "fecha", fecha);
    }
}