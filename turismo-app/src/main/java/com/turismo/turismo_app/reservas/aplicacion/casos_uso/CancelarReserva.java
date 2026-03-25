package com.turismo.turismo_app.reservas.aplicacion.casos_uso;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.entities.Reserva;
import com.turismo.turismo_app.reservas.dominio.entities.EstadoReserva;
import com.turismo.turismo_app.reservas.dominio.exceptions.ReservaException;
import com.turismo.turismo_app.reservas.dominio.ports.ReservaRepositoryPort;

@Component
public class CancelarReserva {

    private final ReservaRepositoryPort reservaRepository;

    public CancelarReserva(ReservaRepositoryPort reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva ejecutar(String reservaId) {

        Reserva reserva = reservaRepository.buscarPorId(reservaId)
                .orElseThrow(() -> new ReservaException("Reserva no encontrada"));

        if (reserva.getEstado() == EstadoReserva.CONFIRMADA) {
            throw new ReservaException("No se puede cancelar una reserva confirmada");
        }

        reserva.cancelar();

        return reservaRepository.guardar(reserva);
    }
}