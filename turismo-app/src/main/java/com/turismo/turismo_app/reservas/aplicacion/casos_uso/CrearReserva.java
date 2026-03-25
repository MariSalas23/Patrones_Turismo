package com.turismo.turismo_app.reservas.aplicacion.casos_uso;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.turismo.turismo_app.reservas.dominio.entities.Reserva;
import com.turismo.turismo_app.reservas.dominio.exceptions.ReservaException;
import com.turismo.turismo_app.reservas.dominio.ports.*;

@Component
public class CrearReserva {

    private final ReservaRepositoryPort reservaRepository;
    private final UsuarioClientPort usuarioClient;
    private final TourClientPort tourClient;

    public CrearReserva(ReservaRepositoryPort reservaRepository,
                        UsuarioClientPort usuarioClient,
                        TourClientPort tourClient) {
        this.reservaRepository = reservaRepository;
        this.usuarioClient = usuarioClient;
        this.tourClient = tourClient;
    }

    public Reserva ejecutar(String usuarioId, String tourId, int cantidadPersonas, LocalDateTime fecha) {

        if (!usuarioClient.existeUsuario(usuarioId)) {
            throw new ReservaException("El usuario no está registrado");
        }

        if (!tourClient.existeTour(tourId)) {
            throw new ReservaException("El tour no existe");
        }

        if (fecha.isBefore(LocalDateTime.now())) {
            throw new ReservaException("No se puede reservar en fechas pasadas");
        }

        int disponibles = tourClient.obtenerCapacidadDisponible(tourId);

        if (cantidadPersonas > disponibles) {
            throw new ReservaException("No hay cupos disponibles");
        }

        Reserva reserva = new Reserva(null, usuarioId, tourId, cantidadPersonas, fecha);

        return reservaRepository.guardar(reserva);
    }
}