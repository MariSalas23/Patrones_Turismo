package com.turismo.turismo_app.reservas.dominio.ports;

public interface TourClientPort {

    boolean existeTour(String tourId);

    int obtenerCapacidadDisponible(String tourId);
}