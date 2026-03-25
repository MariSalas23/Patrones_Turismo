package com.turismo.turismo_app.reservas.aplicacion.casos_uso;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.entities.*;
import com.turismo.turismo_app.reservas.dominio.exceptions.*;
import com.turismo.turismo_app.reservas.dominio.ports.ReservaRepositoryPort;

@Component
public class CancelarReserva {

    private final ReservaRepositoryPort repository;

    public CancelarReserva(ReservaRepositoryPort repository) {
        this.repository = repository;
    }

    public Reserva ejecutar(String id) {

        Reserva reserva = repository.buscarPorId(id)
                .orElseThrow(() -> new ReservaNoEncontradaException(id));

        if (reserva.getEstado() == EstadoReserva.CONFIRMADA) {
            throw new EstadoReservaInvalidoException("No se puede cancelar una reserva confirmada");
        }

        reserva.cancelar();

        return repository.guardar(reserva);
    }
}