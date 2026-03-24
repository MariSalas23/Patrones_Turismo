package com.turismo.turismo_app.usuarios.dominio.ports;

import java.util.List;

import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
}