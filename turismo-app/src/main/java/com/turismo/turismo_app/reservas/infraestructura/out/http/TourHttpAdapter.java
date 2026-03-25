package com.turismo.turismo_app.reservas.infraestructura.out.http;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.ports.TourClientPort;

@Component
public class TourHttpAdapter implements TourClientPort {

    @Override
    public boolean existeTour(String tourId) {
        return true;
    }

    @Override
    public int obtenerCapacidadDisponible(String tourId) {
        return 10; // Simulación
    }
}