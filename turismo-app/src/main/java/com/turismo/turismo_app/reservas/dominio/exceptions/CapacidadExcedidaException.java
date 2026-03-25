package com.turismo.turismo_app.reservas.dominio.exceptions;

public class CapacidadExcedidaException extends ReservaException {
    public CapacidadExcedidaException() {
        super("No hay cupos disponibles en el tour");
    }
}