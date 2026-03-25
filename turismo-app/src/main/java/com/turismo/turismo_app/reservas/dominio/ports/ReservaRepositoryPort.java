package com.turismo.turismo_app.reservas.dominio.ports;

import java.util.List;
import java.util.Optional;

import com.turismo.turismo_app.reservas.dominio.entities.Reserva;

public interface ReservaRepositoryPort {

    Reserva guardar(Reserva reserva);

    Optional<Reserva> buscarPorId(String id);

    List<Reserva> buscarPorUsuario(String usuarioId);
}