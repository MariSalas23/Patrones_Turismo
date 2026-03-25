package com.turismo.turismo_app.usuarios.aplicacion.casos_uso;

import com.turismo.turismo_app.usuarios.dominio.exceptions.UsuarioNoEncontradoException;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

public class EliminarUsuario {

    private final UsuarioRepositoryPort repository;

    public EliminarUsuario(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    public void ejecutar(String id) {

        repository.buscarPorId(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException(id));

        repository.eliminarPorId(id);
    }
}