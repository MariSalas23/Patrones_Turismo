package com.turismo.turismo_app.tours.infraestructura.out.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.tours.dominio.entities.Tour;
import com.turismo.turismo_app.tours.dominio.ports.TourRepositoryPort;
import java.util.Optional;

@Component
public class TourMongoAdapter implements TourRepositoryPort {

    private final SpringDataTourRepository repository;

    public TourMongoAdapter(SpringDataTourRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tour guardar(Tour tour) {
        return repository.save(tour);
    }

    @Override
    public List<Tour> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Tour> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Tour> buscarDisponibles() {
        return repository.findByActivoTrueAndCuposDisponiblesGreaterThan(0);
    }

    @Override
    public boolean existeGuiaEnFecha(String guiaId, LocalDateTime fechaInicio) {
        return repository.existsByGuiaIdAndFechaInicio(guiaId, fechaInicio);
    }
}