package com.turismo.turismo_app.integracion.reservas;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.turismo.turismo_app.reservas.aplicacion.casos_uso.CancelarReserva;
import com.turismo.turismo_app.reservas.aplicacion.casos_uso.ConfirmarReserva;
import com.turismo.turismo_app.reservas.aplicacion.casos_uso.CrearReserva;
import com.turismo.turismo_app.reservas.aplicacion.casos_uso.ModificarReserva;
import com.turismo.turismo_app.reservas.aplicacion.casos_uso.ObtenerReserva;
import com.turismo.turismo_app.reservas.aplicacion.casos_uso.ObtenerTodasReservas;
import com.turismo.turismo_app.reservas.dominio.entities.Reserva;
import com.turismo.turismo_app.reservas.infraestructura.in.controllers.ReservaController;

@WebMvcTest(ReservaController.class)
class ReservaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrearReserva crearReserva;

    @MockBean
    private CancelarReserva cancelarReserva;

    @MockBean
    private ModificarReserva modificarReserva;

    @MockBean
    private ObtenerReserva obtenerReserva;

    @MockBean
    private ObtenerTodasReservas obtenerTodasReservas;

    @MockBean
    private ConfirmarReserva confirmarReserva;

    @Test
    void deberiaCrearReservaPorHttp() throws Exception {
        Reserva reserva = new Reserva("r1", "u1", "t1", 2, LocalDateTime.of(2030, 8, 1, 10, 0));
        reserva.setId("r1");

        when(crearReserva.ejecutar(eq("u1"), eq("t1"), eq(2), any(LocalDateTime.class)))
                .thenReturn(reserva);

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "usuarioId": "u1",
                          "tourId": "t1",
                          "cantidadPersonas": 2,
                          "fecha": "2030-08-01T10:00:00"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("r1"))
                .andExpect(jsonPath("$.usuarioId").value("u1"))
                .andExpect(jsonPath("$.tourId").value("t1"))
                .andExpect(jsonPath("$.cantidadPersonas").value(2))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    void deberiaObtenerReservaPorHttp() throws Exception {
        Reserva reserva = new Reserva("r2", "u2", "t2", 3, LocalDateTime.of(2030, 9, 1, 9, 0));
        reserva.setId("r2");

        when(obtenerReserva.ejecutar("r2")).thenReturn(reserva);

        mockMvc.perform(get("/reservas/r2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("r2"))
                .andExpect(jsonPath("$.usuarioId").value("u2"))
                .andExpect(jsonPath("$.tourId").value("t2"));
    }

    @Test
    void deberiaConfirmarReservaPorHttp() throws Exception {
        Reserva reserva = new Reserva("r3", "u3", "t3", 1, LocalDateTime.of(2030, 10, 1, 15, 0));
        reserva.setId("r3");
        reserva.confirmar();

        when(confirmarReserva.ejecutar("r3")).thenReturn(reserva);

        mockMvc.perform(post("/reservas/r3/confirmar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("r3"))
                .andExpect(jsonPath("$.estado").value("CONFIRMADA"));
    }

    @Test
    void deberiaCancelarReservaPorHttp() throws Exception {
        Reserva reserva = new Reserva("r4", "u4", "t4", 4, LocalDateTime.of(2030, 11, 1, 11, 0));
        reserva.setId("r4");
        reserva.cancelar();

        when(cancelarReserva.ejecutar("r4")).thenReturn(reserva);

        mockMvc.perform(post("/reservas/r4/cancelar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("r4"))
                .andExpect(jsonPath("$.estado").value("CANCELADA"));
    }
}