package com.turismo.turismo_app.tours.aplicacion.casos_uso;

import java.time.LocalDateTime;

import com.turismo.turismo_app.tours.dominio.entities.Tour;
import com.turismo.turismo_app.tours.dominio.exceptions.*;
import com.turismo.turismo_app.tours.dominio.ports.TourRepositoryPort;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.entities.TipoUsuario;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

public class CrearTour {

    private final TourRepositoryPort repository;
    private final UsuarioRepositoryPort usuarioRepository;

    public CrearTour(TourRepositoryPort repository,
                     UsuarioRepositoryPort usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public Tour ejecutar(Tour tour) {

        // 🔥 CAMPOS OBLIGATORIOS
        if (tour.getNombre() == null || tour.getNombre().isBlank())
            throw new CampoInvalidoTourException("nombre");

        if (tour.getUbicacion() == null || tour.getUbicacion().isBlank())
            throw new CampoInvalidoTourException("ubicacion");

        if (tour.getGuiaId() == null || tour.getGuiaId().isBlank())
            throw new CampoInvalidoTourException("guiaId");

        if (tour.getPrecio() <= 0)
            throw new CampoInvalidoTourException("precio");

        if (tour.getCapacidadMaxima() <= 0)
            throw new CapacidadInvalidaException(tour.getCapacidadMaxima());

        // 🔥 VALIDACIÓN DE FECHAS
        if (tour.getFechaInicio() == null || tour.getFechaFin() == null)
            throw new CampoInvalidoTourException("fechas");

        if (tour.getFechaInicio().isBefore(LocalDateTime.now()))
            throw new FechaInvalidaException(String.valueOf(tour.getFechaInicio()));

        if (!tour.getFechaFin().isAfter(tour.getFechaInicio()))
            throw new RangoFechasInvalidoException(
                String.valueOf(tour.getFechaInicio()),
                String.valueOf(tour.getFechaFin())
            );

        // 🔥 VALIDAR USUARIO (GUIA)
        Usuario usuario = usuarioRepository.buscarPorId(tour.getGuiaId())
                .orElseThrow(() -> new GuiaNoExisteException(tour.getGuiaId()));

        if (!usuario.getTipo().equals(TipoUsuario.GUIA))
            throw new GuiaNoExisteException(tour.getGuiaId());

        // 🔥 VALIDAR DISPONIBILIDAD DEL GUIA
        if (repository.existeGuiaEnFecha(tour.getGuiaId(), tour.getFechaInicio()))
            throw new GuiaNoDisponibleException(tour.getGuiaId());

        // ✅ GUARDAR
        return repository.guardar(tour);
    }
}