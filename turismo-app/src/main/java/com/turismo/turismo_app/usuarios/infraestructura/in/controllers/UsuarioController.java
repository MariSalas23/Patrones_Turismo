package com.turismo.turismo_app.usuarios.infraestructura.in.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.turismo.turismo_app.usuarios.aplicacion.casos_uso.*;
import com.turismo.turismo_app.usuarios.dominio.entities.TipoUsuario;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final RegistrarUsuario registrarUsuario;
    private final ObtenerTodosUsuarios obtenerTodosUsuarios;
    private final ObtenerUsuario obtenerUsuario;
    private final ModificarUsuario modificarUsuario;
    private final EliminarUsuario eliminarUsuario;

    public UsuarioController(UsuarioRepositoryPort repository) {
        this.registrarUsuario = new RegistrarUsuario(repository);
        this.obtenerTodosUsuarios = new ObtenerTodosUsuarios(repository);
        this.obtenerUsuario = new ObtenerUsuario(repository); 
        this.modificarUsuario = new ModificarUsuario(repository);
        this.eliminarUsuario = new EliminarUsuario(repository);
    }

    // Crear
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

    // Listar
    @GetMapping
    public List<Usuario> listar() {
        return obtenerTodosUsuarios.ejecutar();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable String id) {
        return obtenerUsuario.ejecutar(id);
    }

    // Modificar
    @PutMapping("/{id}")
    public Usuario modificar(@PathVariable String id,
                             @RequestBody Map<String, String> body) {

        Usuario datos = new Usuario(
            body.get("nombre"),
            body.get("apellido"),
            body.get("correo"),
            TipoUsuario.fromString(body.get("tipo"))
        );

        return modificarUsuario.ejecutar(id, datos);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        eliminarUsuario.ejecutar(id);
    }
}