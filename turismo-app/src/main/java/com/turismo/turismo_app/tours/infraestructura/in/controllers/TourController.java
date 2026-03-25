package com.turismo.turismo_app.tours.infraestructura.in.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.turismo.turismo_app.tours.aplicacion.casos_uso.*;
import com.turismo.turismo_app.tours.dominio.entities.Tour;
import com.turismo.turismo_app.tours.dominio.ports.TourRepositoryPort;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

@RestController
@RequestMapping("/tours")
public class TourController {

    private final CrearTour crearTour;
    private final ConsultarDisponibilidad consultarDisponibilidad;
    private final ObtenerTodosTours obtenerTodosTours;
    private final ObtenerTour obtenerTour;
    private final ModificarTour modificarTour;
    private final CancelarTour cancelarTour;

    public TourController(TourRepositoryPort tourRepo,
                          UsuarioRepositoryPort usuarioRepo) {

        this.crearTour = new CrearTour(tourRepo, usuarioRepo);
        this.consultarDisponibilidad = new ConsultarDisponibilidad(tourRepo);
        this.obtenerTodosTours = new ObtenerTodosTours(tourRepo);
        this.obtenerTour = new ObtenerTour(tourRepo);
        this.modificarTour = new ModificarTour(tourRepo);
        this.cancelarTour = new CancelarTour(tourRepo);
    }

    // 🔥 CREAR TOUR
    @PostMapping
    public Tour crear(@RequestBody Map<String, String> body) {

        Tour tour = new Tour(
            body.get("nombre"),
            body.get("ubicacion"),
            body.get("guiaId"),
            Integer.parseInt(body.get("capacidadMaxima")),
            Double.parseDouble(body.get("precio")),
            LocalDateTime.parse(body.get("fechaInicio")),
            LocalDateTime.parse(body.get("fechaFin"))
        );

        return crearTour.ejecutar(tour);
    }

    // 🔥 CONSULTAR DISPONIBLES
    @GetMapping("/disponibles")
    public List<Tour> disponibles() {
        return consultarDisponibilidad.ejecutar();
    }

    // 🔥 OBTENER TODOS
    @GetMapping
    public List<Tour> listar() {
        return obtenerTodosTours.ejecutar();
    }

    // 🔥 OBTENER POR ID
    @GetMapping("/{id}")
    public Tour obtener(@PathVariable String id) {
        return obtenerTour.ejecutar(id);
    }

    // 🔥 MODIFICAR
    @PutMapping("/{id}")
    public Tour modificar(@PathVariable String id,
                          @RequestBody Map<String, String> body) {

        Tour datos = new Tour(
            body.get("nombre"),
            body.get("ubicacion"),
            body.get("guiaId"),
            Integer.parseInt(body.get("capacidadMaxima")),
            Double.parseDouble(body.get("precio")),
            LocalDateTime.parse(body.get("fechaInicio")),
            LocalDateTime.parse(body.get("fechaFin"))
        );

        return modificarTour.ejecutar(id, datos);
    }

    // 🔥 CANCELAR TOUR
    @PutMapping("/{id}/cancelar")
    public Tour cancelar(@PathVariable String id) {
        return cancelarTour.ejecutar(id);
    }
}