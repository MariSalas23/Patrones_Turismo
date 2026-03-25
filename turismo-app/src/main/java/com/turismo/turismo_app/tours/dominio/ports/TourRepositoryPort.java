package com.turismo.turismo_app.tours.dominio.ports;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.turismo.turismo_app.tours.dominio.entities.Tour;

public interface TourRepositoryPort {

    Tour guardar(Tour tour);

    List<Tour> buscarTodos();

    List<Tour> buscarDisponibles();

    boolean existeGuiaEnFecha(String guiaId, LocalDateTime fecha);

    Optional<Tour> buscarPorId(String id);
}