package com.turismo.turismo_app.reservas.infraestructura.in.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.turismo.turismo_app.reservas.aplicacion.casos_uso.*;
import com.turismo.turismo_app.reservas.dominio.entities.Reserva;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final CrearReserva crearReserva;
    private final CancelarReserva cancelarReserva;
    private final ModificarReserva modificarReserva;
    private final ObtenerReserva obtenerReserva;
    private final ObtenerTodasReservas obtenerTodas;
    private final ConfirmarReserva confirmarReserva;

    public ReservaController(CrearReserva crearReserva,
                             CancelarReserva cancelarReserva,
                             ModificarReserva modificarReserva,
                             ObtenerReserva obtenerReserva,
                             ObtenerTodasReservas obtenerTodas, ConfirmarReserva confirmarReserva) {
        this.crearReserva = crearReserva;
        this.cancelarReserva = cancelarReserva;
        this.modificarReserva = modificarReserva;
        this.obtenerReserva = obtenerReserva;
        this.obtenerTodas = obtenerTodas;
        this.confirmarReserva = confirmarReserva;
    }

    @PostMapping("/{id}/confirmar")
    public Reserva confirmar(@PathVariable String id) {
        return confirmarReserva.ejecutar(id);
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

    @GetMapping("/{id}")
    public Reserva obtener(@PathVariable String id) {
        return obtenerReserva.ejecutar(id);
    }

    @GetMapping
    public List<Reserva> obtenerTodas() {
        return obtenerTodas.ejecutar();
    }

    @PutMapping("/{id}")
    public Reserva modificar(@PathVariable String id,
                             @RequestBody ReservaRequest request) {
        return modificarReserva.ejecutar(
                id,
                request.getCantidadPersonas(),
                request.getFecha()
        );
    }

    @PostMapping("/{id}/cancelar")
    public Reserva cancelar(@PathVariable String id) {
        return cancelarReserva.ejecutar(id);
    }
}