package com.turismo.turismo_app.reservas.aplicacion.casos_uso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.entities.Reserva;
import com.turismo.turismo_app.reservas.dominio.ports.ReservaRepositoryPort;

@Component
public class ObtenerTodasReservas {

    private final ReservaRepositoryPort repository;

    public ObtenerTodasReservas(ReservaRepositoryPort repository) {
        this.repository = repository;
    }

    public List<Reserva> ejecutar() {
        return repository.buscarTodas();
    }
}