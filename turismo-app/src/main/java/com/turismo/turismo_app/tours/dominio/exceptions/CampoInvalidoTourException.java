package com.turismo.turismo_app.tours.dominio.exceptions;

public class CampoInvalidoTourException extends TourException {
    public CampoInvalidoTourException(String campo) {
        super("Campo obligatorio o inválido", campo, null);
    }
}