package com.turismo.turismo_app.tours.dominio.exceptions;

public class CapacidadInvalidaException extends TourException {
    public CapacidadInvalidaException(int valor) {
        super("Capacidad inválida", "capacidadMaxima", String.valueOf(valor));
    }
}