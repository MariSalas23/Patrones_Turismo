package com.turismo.turismo_app.tours.dominio.exceptions;

public class GuiaNoExisteException extends TourException {

    public GuiaNoExisteException(String guiaId) {
        super("El guía no existe", "guiaId", guiaId);
    }
}