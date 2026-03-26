package com.turismo.turismo_app.usuarios.aplicacion.casos_uso;

import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.exceptions.UsuarioNoEncontradoException;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

public class ObtenerUsuario {

    private final UsuarioRepositoryPort repository;

    public ObtenerUsuario(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    public Usuario ejecutar(String id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));
    }
}