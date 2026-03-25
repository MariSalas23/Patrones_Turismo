package com.turismo.turismo_app.tours.aplicacion.casos_uso;

import com.turismo.turismo_app.tours.dominio.entities.Tour;
import com.turismo.turismo_app.tours.dominio.ports.TourRepositoryPort;

public class CancelarTour {

    private final TourRepositoryPort repository;

    public CancelarTour(TourRepositoryPort repository) {
        this.repository = repository;
    }

    public Tour ejecutar(Tour tour) {
        tour.setActivo(false);
        return repository.guardar(tour);
    }
}