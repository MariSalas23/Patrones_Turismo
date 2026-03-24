package com.turismo.turismo_app.usuarios.infraestructura.in.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turismo.turismo_app.usuarios.dominio.entities.TipoUsuario;
import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepositoryPort repository;

    public UsuarioController(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    @PostMapping
    public Usuario crear(@RequestBody Map<String, String> body) {

        String nombre = body.get("nombre");
        String correo = body.get("correo");
        TipoUsuario tipo = TipoUsuario.fromString(body.get("tipo"));

        Usuario usuario = new Usuario(nombre, correo, tipo);

        return repository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }
}