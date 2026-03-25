package com.turismo.turismo_app.reservas.dominio.exceptions;

public class EstadoReservaInvalidoException extends ReservaException {
    public EstadoReservaInvalidoException(String mensaje) {
        super(mensaje);
    }
}