package com.turismo.turismo_app.tours.dominio.exceptions;

public class RangoFechasInvalidoException extends TourException {

    public RangoFechasInvalidoException(String inicio, String fin) {
        super("La fecha de fin debe ser posterior a la fecha de inicio",
              "fecha", inicio + " - " + fin);
    }
}