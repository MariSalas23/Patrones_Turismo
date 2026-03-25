package com.turismo.turismo_app.tours.aplicacion.casos_uso;

import java.time.LocalDateTime;

import com.turismo.turismo_app.tours.dominio.entities.Tour;
import com.turismo.turismo_app.tours.dominio.exceptions.*;
import com.turismo.turismo_app.tours.dominio.ports.TourRepositoryPort;
import com.turismo.turismo_app.tours.dominio.entities.Tour;

public class ModificarTour {

    private final TourRepositoryPort repository;

    public ModificarTour(TourRepositoryPort repository) {
        this.repository = repository;
    }

    public Tour ejecutar(String id, Tour datos) {

        Tour tour = repository.buscarPorId(id)
                .orElseThrow(() -> new TourException("Tour no encontrado", "id", id));

        // 🔥 VALIDACIONES

        if (datos.getNombre() == null || datos.getNombre().isBlank())
            throw new CampoInvalidoTourException("nombre");

        if (datos.getUbicacion() == null || datos.getUbicacion().isBlank())
            throw new CampoInvalidoTourException("ubicacion");

        if (datos.getPrecio() <= 0)
            throw new CampoInvalidoTourException("precio");

        if (datos.getCapacidadMaxima() <= 0)
            throw new CapacidadInvalidaException(datos.getCapacidadMaxima());

        if (datos.getFechaInicio() == null || datos.getFechaFin() == null)
            throw new CampoInvalidoTourException("fechas");

        if (datos.getFechaInicio().isBefore(LocalDateTime.now()))
            throw new FechaInvalidaException(String.valueOf(datos.getFechaInicio()));

        if (!datos.getFechaFin().isAfter(datos.getFechaInicio()))
            throw new RangoFechasInvalidoException(
                String.valueOf(datos.getFechaInicio()),
                String.valueOf(datos.getFechaFin())
            );

        // 🔥 ACTUALIZAR
        tour.setNombre(datos.getNombre());
        tour.setUbicacion(datos.getUbicacion());
        tour.setPrecio(datos.getPrecio());
        tour.setCapacidadMaxima(datos.getCapacidadMaxima());
        tour.setFechaInicio(datos.getFechaInicio());
        tour.setFechaFin(datos.getFechaFin());

        return repository.guardar(tour);
    }
}