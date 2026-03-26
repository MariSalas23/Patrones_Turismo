package com.turismo.turismo_app.reservas.aplicacion.casos_uso;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.entities.Reserva;
import com.turismo.turismo_app.reservas.dominio.entities.EstadoReserva;
import com.turismo.turismo_app.reservas.dominio.exceptions.EstadoReservaInvalidoException;
import com.turismo.turismo_app.reservas.dominio.exceptions.ReservaNoEncontradaException;
import com.turismo.turismo_app.reservas.dominio.ports.*;

@Component
public class ConfirmarReserva {

    private final ReservaRepositoryPort repository;
    private final PagoPort pagoPort;

    public ConfirmarReserva(ReservaRepositoryPort repository,
                            PagoPort pagoPort) {
        this.repository = repository;
        this.pagoPort = pagoPort;
    }

    public Reserva ejecutar(String id) {

        Reserva reserva = repository.buscarPorId(id)
                .orElseThrow(() -> new ReservaNoEncontradaException(id));

        // No confirmar si ya está cancelada
        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new EstadoReservaInvalidoException("No se puede confirmar una reserva cancelada");
        }

        // Simulación de pago
        boolean pagoExitoso = pagoPort.procesarPago(id);

        if (!pagoExitoso) {
            throw new EstadoReservaInvalidoException("El pago no fue aprobado");
        }

        // Confirmar
        reserva.confirmar();

        return repository.guardar(reserva);
    }
}