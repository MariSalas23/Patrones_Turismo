package com.turismo.turismo_app.reservas.dominio.ports;

public interface PagoPort {

    boolean procesarPago(String reservaId);
}