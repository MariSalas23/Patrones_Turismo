package com.turismo.turismo_app.unitarias.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.turismo.turismo_app.usuarios.dominio.entities.TipoUsuario;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.infraestructura.out.persistance.SpringDataUsuarioRepository;
import com.turismo.turismo_app.usuarios.infraestructura.out.persistance.UsuarioMongoAdapter;

class UsuarioMongoAdapterTest {

    private SpringDataUsuarioRepository repository;
    private UsuarioMongoAdapter adapter;

    @BeforeEach
    void setUp() {
        repository = mock(SpringDataUsuarioRepository.class);
        adapter = new UsuarioMongoAdapter(repository);
    }

    @Test
    void deberiaGuardarUsuarioDelegandoEnRepositorio() {
        Usuario usuario = new Usuario("Laura", "laura@gmail.com", TipoUsuario.CLIENTE);
        when(repository.save(usuario)).thenReturn(usuario);

        Usuario resultado = adapter.save(usuario);

        assertEquals(usuario, resultado);
        verify(repository, times(1)).save(usuario);
    }

    @Test
    void deberiaListarUsuariosDelegandoEnRepositorio() {
        List<Usuario> usuarios = List.of(
                new Usuario("Laura", "laura@gmail.com", TipoUsuario.CLIENTE),
                new Usuario("Carlos", "carlos@gmail.com", TipoUsuario.GUIA)
        );

        when(repository.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = adapter.findAll();

        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }
}