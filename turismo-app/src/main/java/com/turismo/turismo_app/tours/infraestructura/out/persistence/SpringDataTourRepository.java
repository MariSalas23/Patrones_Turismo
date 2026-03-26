package com.turismo.turismo_app.tours.infraestructura.out.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.turismo.turismo_app.tours.dominio.entities.Tour;

@Repository
public interface SpringDataTourRepository extends MongoRepository<Tour, String> {

    // Tours disponibles
    List<Tour> findByActivoTrueAndCuposDisponiblesGreaterThan(int cupos);

    // Validar guía
    boolean existsByGuiaIdAndFechaInicio(String guiaId, LocalDateTime fechaInicio);
}