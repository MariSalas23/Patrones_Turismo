package com.turismo.turismo_app.tours.aplicacion.casos_uso;

import java.util.Optional;

import com.turismo.turismo_app.tours.dominio.entities.Tour;
import com.turismo.turismo_app.tours.dominio.exceptions.TourException;
import com.turismo.turismo_app.tours.dominio.ports.TourRepositoryPort;
import com.turismo.turismo_app.tours.dominio.entities.Tour;

public class ObtenerTour {

    private final TourRepositoryPort repository;

    public ObtenerTour(TourRepositoryPort repository) {
        this.repository = repository;
    }

    public Tour ejecutar(String id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new TourException("Tour no encontrado", "id", id));
    }
}