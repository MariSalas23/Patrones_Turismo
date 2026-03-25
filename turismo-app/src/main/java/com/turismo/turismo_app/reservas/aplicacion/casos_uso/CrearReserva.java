package com.turismo.turismo_app.reservas.aplicacion.casos_uso;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.entities.Reserva;
import com.turismo.turismo_app.reservas.dominio.exceptions.*;
import com.turismo.turismo_app.reservas.dominio.ports.*;

@Component
public class CrearReserva {

    private final ReservaRepositoryPort repository;
    private final UsuarioClientPort usuarioClient;
    private final TourClientPort tourClient;

    public CrearReserva(ReservaRepositoryPort repository,
                        UsuarioClientPort usuarioClient,
                        TourClientPort tourClient) {
        this.repository = repository;
        this.usuarioClient = usuarioClient;
        this.tourClient = tourClient;
    }

    public Reserva ejecutar(String usuarioId, String tourId, int cantidad, LocalDateTime fecha) {

        if (!usuarioClient.existeUsuario(usuarioId)) {
            throw new ReservaException("Usuario no existe");
        }

        if (fecha.isBefore(LocalDateTime.now())) {
            throw new FechaInvalidaReservaException();
        }

        int disponibles = tourClient.obtenerCapacidadDisponible(tourId);

        if (cantidad > disponibles) {
            throw new CapacidadExcedidaException();
        }

        Reserva reserva = new Reserva(null, usuarioId, tourId, cantidad, fecha);

        return repository.guardar(reserva);
    }
}