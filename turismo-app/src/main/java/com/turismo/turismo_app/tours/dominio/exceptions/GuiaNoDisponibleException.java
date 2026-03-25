package com.turismo.turismo_app.tours.dominio.exceptions;

public class GuiaNoDisponibleException extends TourException {
    public GuiaNoDisponibleException(String guiaId) {
        super("El guía ya tiene un tour asignado en esa fecha", "guiaId", guiaId);
    }
}