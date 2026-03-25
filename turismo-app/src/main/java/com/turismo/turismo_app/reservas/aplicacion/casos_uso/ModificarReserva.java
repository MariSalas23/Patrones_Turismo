package com.turismo.turismo_app.reservas.aplicacion.casos_uso;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.entities.*;
import com.turismo.turismo_app.reservas.dominio.exceptions.*;
import com.turismo.turismo_app.reservas.dominio.ports.*;

@Component
public class ModificarReserva {

    private final ReservaRepositoryPort repository;
    private final TourClientPort tourClient;

    public ModificarReserva(ReservaRepositoryPort repository, TourClientPort tourClient) {
        this.repository = repository;
        this.tourClient = tourClient;
    }

    public Reserva ejecutar(String id, int cantidad, LocalDateTime fecha) {

        Reserva reserva = repository.buscarPorId(id)
                .orElseThrow(() -> new ReservaNoEncontradaException(id));

        if (reserva.getEstado() == EstadoReserva.CONFIRMADA) {
            throw new EstadoReservaInvalidoException("No se puede modificar una reserva confirmada");
        }

        if (fecha.isBefore(LocalDateTime.now())) {
            throw new FechaInvalidaReservaException();
        }

        int disponibles = tourClient.obtenerCapacidadDisponible(reserva.getTourId());

        if (cantidad > disponibles) {
            throw new CapacidadExcedidaException();
        }

        Reserva nueva = new Reserva(
                reserva.getId(),
                reserva.getUsuarioId(),
                reserva.getTourId(),
                cantidad,
                fecha
        );

        return repository.guardar(nueva);
    }
}