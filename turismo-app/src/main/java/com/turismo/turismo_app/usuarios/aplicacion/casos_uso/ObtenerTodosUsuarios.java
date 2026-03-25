package com.turismo.turismo_app.usuarios.aplicacion.casos_uso;

import java.util.List;

import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

public class ObtenerTodosUsuarios {

    private final UsuarioRepositoryPort repository;

    public ObtenerTodosUsuarios(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    public List<Usuario> ejecutar() {
        return repository.buscarTodos();
    }
}