package com.turismo.turismo_app.reservas.aplicacion.casos_uso;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.entities.Reserva;
import com.turismo.turismo_app.reservas.dominio.entities.EstadoReserva;
import com.turismo.turismo_app.reservas.dominio.exceptions.ReservaException;
import com.turismo.turismo_app.reservas.dominio.ports.*;

@Component
public class ConfirmarReserva {

    private final ReservaRepositoryPort reservaRepository;
    private final PagoPort pagoPort;

    public ConfirmarReserva(ReservaRepositoryPort reservaRepository, PagoPort pagoPort) {
        this.reservaRepository = reservaRepository;
        this.pagoPort = pagoPort;
    }

    public Reserva ejecutar(String reservaId) {

        Reserva reserva = reservaRepository.buscarPorId(reservaId)
                .orElseThrow(() -> new ReservaException("Reserva no encontrada"));

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new ReservaException("Reserva cancelada");
        }

        if (!pagoPort.procesarPago(reservaId)) {
            throw new ReservaException("Pago rechazado");
        }

        reserva.confirmar();

        return reservaRepository.guardar(reserva);
    }
}