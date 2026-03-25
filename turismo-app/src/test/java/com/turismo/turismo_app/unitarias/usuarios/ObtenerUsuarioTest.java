package com.turismo.turismo_app.unitarias.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.turismo.turismo_app.usuarios.dominio.entities.TipoUsuario;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;
import com.turismo.turismo_app.usuarios.infraestructura.in.controllers.UsuarioController;

class ObtenerUsuarioTest {

    private UsuarioRepositoryPort repository;
    private UsuarioController controller;

    @BeforeEach
    void setUp() {
        repository = mock(UsuarioRepositoryPort.class);
        controller = new UsuarioController(repository);
    }

    @Test
    void deberiaListarUsuarios() {
        List<Usuario> usuarios = List.of(
                new Usuario("Laura", "Gomez", "laura@gmail.com", TipoUsuario.CLIENTE),
                new Usuario("Carlos", "Perez", "carlos@gmail.com", TipoUsuario.GUIA)
        );

        when(repository.buscarTodos()).thenReturn(usuarios);

        List<Usuario> respuesta = controller.listar();

        assertEquals(2, respuesta.size());
        assertEquals("Laura", respuesta.get(0).getNombre());
        assertEquals("Carlos", respuesta.get(1).getNombre());
        verify(repository, times(1)).buscarTodos();
    }

    @Test
    void deberiaRetornarListaVaciaCuandoNoHayUsuarios() {
        when(repository.buscarTodos()).thenReturn(List.of());

        List<Usuario> respuesta = controller.listar();

        assertEquals(0, respuesta.size());
        verify(repository, times(1)).buscarTodos();
    }
}
