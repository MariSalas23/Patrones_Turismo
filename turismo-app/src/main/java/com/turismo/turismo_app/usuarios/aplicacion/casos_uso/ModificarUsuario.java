package com.turismo.turismo_app.usuarios.aplicacion.casos_uso;

import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.exceptions.*;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

public class ModificarUsuario {

    private final UsuarioRepositoryPort repository;

    public ModificarUsuario(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    public Usuario ejecutar(String id, Usuario datos) {

        Usuario usuario = repository.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException(id));

        // 🔥 VALIDACIONES
        if (datos.getNombre() == null || datos.getNombre().isBlank())
            throw new CampoVacioException("nombre");

        if (!datos.getNombre().matches("[a-zA-Z ]+"))
            throw new NombreInvalidoException(datos.getNombre());

        if (datos.getCorreo() == null || datos.getCorreo().isBlank())
            throw new CampoVacioException("correo");

        if (!datos.getCorreo().contains("@"))
            throw new CorreoInvalidoException(datos.getCorreo());

        // 🔥 VALIDAR DUPLICADO (si cambia)
        if (!usuario.getCorreo().equalsIgnoreCase(datos.getCorreo())
                && repository.existePorCorreo(datos.getCorreo())) {
            throw new CorreoDuplicadoException(datos.getCorreo());
        }

        // 🔥 ACTUALIZAR
        usuario.setNombre(datos.getNombre());
        usuario.setCorreo(datos.getCorreo());
        usuario.setTipo(datos.getTipo());

        return repository.guardar(usuario);
    }
}