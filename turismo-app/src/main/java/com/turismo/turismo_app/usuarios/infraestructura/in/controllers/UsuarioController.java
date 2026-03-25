package com.turismo.turismo_app.usuarios.infraestructura.in.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.turismo.turismo_app.usuarios.aplicacion.casos_uso.ObtenerTodosUsuarios;
import com.turismo.turismo_app.usuarios.aplicacion.casos_uso.RegistrarUsuario;
import com.turismo.turismo_app.usuarios.dominio.entities.TipoUsuario;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final RegistrarUsuario registrarUsuario;
    private final ObtenerTodosUsuarios obtenerTodosUsuarios;

    public UsuarioController(UsuarioRepositoryPort repository) {
        this.registrarUsuario = new RegistrarUsuario(repository);
        this.obtenerTodosUsuarios = new ObtenerTodosUsuarios(repository);
    }

    @PostMapping
    public Usuario crear(@RequestBody Map<String, String> body) {

        Usuario usuario = new Usuario(
            body.get("nombre"),
            body.get("apellido"),
            body.get("correo"),
            TipoUsuario.fromString(body.get("tipo"))
        );

        return registrarUsuario.ejecutar(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return obtenerTodosUsuarios.ejecutar();
    }
}