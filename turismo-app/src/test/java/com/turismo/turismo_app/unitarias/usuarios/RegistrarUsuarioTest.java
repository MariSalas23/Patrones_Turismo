package com.turismo.turismo_app.unitarias.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.turismo.turismo_app.usuarios.dominio.entities.TipoUsuario;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;
import com.turismo.turismo_app.usuarios.infraestructura.in.controllers.UsuarioController;

class RegistrarUsuarioTest {

    private UsuarioRepositoryPort repository;
    private UsuarioController controller;

    @BeforeEach
    void setUp() {
        repository = mock(UsuarioRepositoryPort.class);
        controller = new UsuarioController(repository);
    }

    @Test
    void deberiaRegistrarUsuarioCliente() {
        when(repository.existePorCorreo("laura@gmail.com")).thenReturn(false);
        when(repository.guardar(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setId("1");
            return usuario;
        });

        Map<String, String> body = Map.of(
                "nombre", "Laura",
                "apellido", "Gomez",
                "correo", "laura@gmail.com",
                "tipo", "CLIENTE"
        );

        Usuario respuesta = controller.crear(body);

        assertNotNull(respuesta);
        assertEquals("1", respuesta.getId());
        assertEquals("Laura", respuesta.getNombre());
        assertEquals("Gomez", respuesta.getApellido());
        assertEquals("laura@gmail.com", respuesta.getCorreo());
        assertEquals(TipoUsuario.CLIENTE, respuesta.getTipo());

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        verify(repository, times(1)).guardar(captor.capture());

        Usuario guardado = captor.getValue();
        assertEquals("Laura", guardado.getNombre());
        assertEquals("Gomez", guardado.getApellido());
        assertEquals("laura@gmail.com", guardado.getCorreo());
        assertEquals(TipoUsuario.CLIENTE, guardado.getTipo());
    }

    @Test
    void deberiaRegistrarUsuarioGuia() {
        when(repository.existePorCorreo("carlos@gmail.com")).thenReturn(false);
        when(repository.guardar(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Map<String, String> body = Map.of(
                "nombre", "Carlos",
                "apellido", "Perez",
                "correo", "carlos@gmail.com",
                "tipo", "GUIA"
        );

        Usuario respuesta = controller.crear(body);

        assertEquals("Carlos", respuesta.getNombre());
        assertEquals("Perez", respuesta.getApellido());
        assertEquals(TipoUsuario.GUIA, respuesta.getTipo());
        verify(repository, times(1)).guardar(any(Usuario.class));
    }

    @Test
    void deberiaLanzarExcepcionCuandoTipoEsInvalido() {
        Map<String, String> body = Map.of(
                "nombre", "Pedro",
                "apellido", "Lopez",
                "correo", "pedro@gmail.com",
                "tipo", "ADMIN"
        );

        assertThrows(IllegalArgumentException.class, () -> controller.crear(body));
    }

    @Test
    void deberiaLanzarExcepcionCuandoFaltaTipo() {
        Map<String, String> body = Map.of(
                "nombre", "Sara",
                "apellido", "Ruiz",
                "correo", "sara@gmail.com"
        );

        assertThrows(IllegalArgumentException.class, () -> controller.crear(body));
    }
}