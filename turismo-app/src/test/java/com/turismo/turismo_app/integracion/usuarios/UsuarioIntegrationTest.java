package com.turismo.turismo_app.integracion.usuarios;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.turismo.turismo_app.usuarios.dominio.entities.TipoUsuario;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;
import com.turismo.turismo_app.usuarios.infraestructura.in.controllers.UsuarioController;

@WebMvcTest(UsuarioController.class)
class UsuarioIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepositoryPort repository;

    @Test
    void deberiaCrearUsuarioPorHttp() throws Exception {
        Usuario usuario = new Usuario("Laura", "laura@gmail.com", TipoUsuario.CLIENTE);
        usuario.setId("abc123");

        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "nombre": "Laura",
                          "correo": "laura@gmail.com",
                          "tipo": "CLIENTE"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("abc123"))
                .andExpect(jsonPath("$.nombre").value("Laura"))
                .andExpect(jsonPath("$.correo").value("laura@gmail.com"))
                .andExpect(jsonPath("$.tipo").value("CLIENTE"));
    }

    @Test
    void deberiaListarUsuariosPorHttp() throws Exception {
        Usuario u1 = new Usuario("Laura", "laura@gmail.com", TipoUsuario.CLIENTE);
        Usuario u2 = new Usuario("Carlos", "carlos@gmail.com", TipoUsuario.GUIA);

        when(repository.findAll()).thenReturn(List.of(u1, u2));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Laura"))
                .andExpect(jsonPath("$[0].tipo").value("CLIENTE"))
                .andExpect(jsonPath("$[1].nombre").value("Carlos"))
                .andExpect(jsonPath("$[1].tipo").value("GUIA"));
    }
}