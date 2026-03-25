package com.turismo.turismo_app.unitarias.tours;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.turismo.turismo_app.tours.aplicacion.casos_uso.CrearTour;
import com.turismo.turismo_app.tours.dominio.entities.Tour;
import com.turismo.turismo_app.tours.dominio.exceptions.CampoInvalidoTourException;
import com.turismo.turismo_app.tours.dominio.exceptions.FechaInvalidaException;
import com.turismo.turismo_app.tours.dominio.exceptions.GuiaNoDisponibleException;
import com.turismo.turismo_app.tours.dominio.exceptions.GuiaNoExisteException;
import com.turismo.turismo_app.tours.dominio.ports.TourRepositoryPort;
import com.turismo.turismo_app.usuarios.dominio.entities.TipoUsuario;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

class CrearTourTest {

    private TourRepositoryPort repository;
    private UsuarioRepositoryPort usuarioRepository;
    private CrearTour crearTour;

    @BeforeEach
    void setUp() {
        repository = mock(TourRepositoryPort.class);
        usuarioRepository = mock(UsuarioRepositoryPort.class);
        crearTour = new CrearTour(repository, usuarioRepository);
    }

    private Tour tourValido() {
        return new Tour(
                "Tour Bogotá",
                "Bogotá",
                "g1",
                10,
                150000,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(2)
        );
    }

    @Test
    void deberiaCrearTourValido() {
        Tour tour = tourValido();
        Usuario guia = new Usuario("Carlos", "Pérez", "carlos@gmail.com", TipoUsuario.GUIA);
        guia.setId("g1");

        when(usuarioRepository.buscarPorId("g1")).thenReturn(Optional.of(guia));
        when(repository.existeGuiaEnFecha(eq("g1"), any(LocalDateTime.class))).thenReturn(false);
        when(repository.guardar(any(Tour.class))).thenAnswer(invocation -> {
            Tour guardado = invocation.getArgument(0);
            guardado.setId("t1");
            return guardado;
        });

        Tour resultado = crearTour.ejecutar(tour);

        assertNotNull(resultado);
        assertEquals("t1", resultado.getId());
        assertEquals("Tour Bogotá", resultado.getNombre());
        assertEquals("Bogotá", resultado.getUbicacion());
        assertEquals(10, resultado.getCapacidadMaxima());
        verify(repository, times(1)).guardar(any(Tour.class));
    }

    @Test
    void noDeberiaCrearTourSinUbicacion() {
        Tour tour = tourValido();
        tour.setUbicacion(" ");

        assertThrows(CampoInvalidoTourException.class, () -> crearTour.ejecutar(tour));
        verify(repository, never()).guardar(any(Tour.class));
    }

    @Test
    void noDeberiaCrearTourConFechaPasada() {
        Tour tour = tourValido();
        tour.setFechaInicio(LocalDateTime.now().minusDays(1));

        assertThrows(FechaInvalidaException.class, () -> crearTour.ejecutar(tour));
        verify(repository, never()).guardar(any(Tour.class));
    }

    @Test
    void noDeberiaCrearTourSiElGuiaNoExiste() {
        Tour tour = tourValido();

        when(usuarioRepository.buscarPorId("g1")).thenReturn(Optional.empty());

        assertThrows(GuiaNoExisteException.class, () -> crearTour.ejecutar(tour));
        verify(repository, never()).guardar(any(Tour.class));
    }

    @Test
    void noDeberiaCrearTourSiElGuiaYaTieneTourEnEsaFecha() {
        Tour tour = tourValido();
        Usuario guia = new Usuario("Carlos", "Pérez", "carlos@gmail.com", TipoUsuario.GUIA);
        guia.setId("g1");

        when(usuarioRepository.buscarPorId("g1")).thenReturn(Optional.of(guia));
        when(repository.existeGuiaEnFecha(eq("g1"), any(LocalDateTime.class))).thenReturn(true);

        assertThrows(GuiaNoDisponibleException.class, () -> crearTour.ejecutar(tour));
        verify(repository, never()).guardar(any(Tour.class));
    }
}