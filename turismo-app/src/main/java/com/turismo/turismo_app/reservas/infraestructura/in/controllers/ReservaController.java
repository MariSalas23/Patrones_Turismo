package com.turismo.turismo_app.reservas.infraestructura.in.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.turismo.turismo_app.reservas.aplicacion.casos_uso.*;
import com.turismo.turismo_app.reservas.dominio.entities.Reserva;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final CrearReserva crearReserva;
    private final ConfirmarReserva confirmarReserva;
    private final CancelarReserva cancelarReserva;

    public ReservaController(CrearReserva crearReserva,
                             ConfirmarReserva confirmarReserva,
                             CancelarReserva cancelarReserva) {
        this.crearReserva = crearReserva;
        this.confirmarReserva = confirmarReserva;
        this.cancelarReserva = cancelarReserva;
    }

    @PostMapping
    public Reserva crear(@RequestBody ReservaRequest request) {
        return crearReserva.ejecutar(
                request.getUsuarioId(),
                request.getTourId(),
                request.getCantidadPersonas(),
                request.getFecha()
        );
    }

    @PostMapping("/{id}/confirmar")
    public Reserva confirmar(@PathVariable String id) {
        return confirmarReserva.ejecutar(id);
    }

    @PostMapping("/{id}/cancelar")
    public Reserva cancelar(@PathVariable String id) {
        return cancelarReserva.ejecutar(id);
    }
}