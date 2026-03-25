package com.turismo.turismo_app.tours.infraestructura.in.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.turismo.turismo_app.tours.aplicacion.casos_uso.ConsultarDisponibilidad;
import com.turismo.turismo_app.tours.aplicacion.casos_uso.CrearTour;
import com.turismo.turismo_app.tours.dominio.entities.Tour;
import com.turismo.turismo_app.tours.dominio.ports.TourRepositoryPort;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

@RestController
@RequestMapping("/tours")
public class TourController {

    private final CrearTour crearTour;
    private final ConsultarDisponibilidad consultarDisponibilidad;

    public TourController(TourRepositoryPort tourRepo,
                          UsuarioRepositoryPort usuarioRepo) {

        this.crearTour = new CrearTour(tourRepo, usuarioRepo);
        this.consultarDisponibilidad = new ConsultarDisponibilidad(tourRepo);
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
}