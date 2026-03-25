package com.turismo.turismo_app.tours.aplicacion.casos_uso;

import java.util.List;

import com.turismo.turismo_app.tours.dominio.entities.Tour;
import com.turismo.turismo_app.tours.dominio.ports.TourRepositoryPort;

public class ObtenerTodosTours {

    private final TourRepositoryPort repository;

    public ObtenerTodosTours(TourRepositoryPort repository) {
        this.repository = repository;
    }

    public List<Tour> ejecutar() {
        return repository.buscarTodos();
    }
}