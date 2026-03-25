package com.turismo.turismo_app.reservas.aplicacion.casos_uso;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.entities.Reserva;
import com.turismo.turismo_app.reservas.dominio.exceptions.ReservaNoEncontradaException;
import com.turismo.turismo_app.reservas.dominio.ports.ReservaRepositoryPort;

@Component
public class ObtenerReserva {

    private final ReservaRepositoryPort repository;

    public ObtenerReserva(ReservaRepositoryPort repository) {
        this.repository = repository;
    }

    public Reserva ejecutar(String id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ReservaNoEncontradaException(id));
    }
}