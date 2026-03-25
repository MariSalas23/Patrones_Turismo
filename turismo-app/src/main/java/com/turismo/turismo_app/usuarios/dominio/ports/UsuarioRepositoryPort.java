package com.turismo.turismo_app.usuarios.dominio.ports;

import java.util.List;
import java.util.Optional;

import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;

public interface UsuarioRepositoryPort {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(String id);

    List<Usuario> buscarTodos();

    // 🔥 NUEVOS
    void eliminarPorId(String id);

    boolean existePorCorreo(String correo);
}