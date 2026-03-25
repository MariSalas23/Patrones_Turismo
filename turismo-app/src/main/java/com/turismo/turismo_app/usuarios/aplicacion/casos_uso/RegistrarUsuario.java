package com.turismo.turismo_app.usuarios.aplicacion.casos_uso;

import java.util.regex.Pattern;

import com.turismo.turismo_app.usuarios.dominio.entities.Usuario;
import com.turismo.turismo_app.usuarios.dominio.exceptions.CampoVacioException;
import com.turismo.turismo_app.usuarios.dominio.exceptions.CorreoDuplicadoException;
import com.turismo.turismo_app.usuarios.dominio.exceptions.CorreoInvalidoException;
import com.turismo.turismo_app.usuarios.dominio.exceptions.NombreInvalidoException;
import com.turismo.turismo_app.usuarios.dominio.ports.UsuarioRepositoryPort;

public class RegistrarUsuario {

    private final UsuarioRepositoryPort repository;

    public RegistrarUsuario(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    public Usuario ejecutar(Usuario usuario) {

        // 🔥 VALIDAR CAMPOS VACÍOS
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new CampoVacioException("nombre");
        }

        if (usuario.getApellido() == null || usuario.getApellido().isBlank()) {
            throw new CampoVacioException("apellido");
        }

        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            throw new CampoVacioException("correo");
        }

        // 🔥 VALIDAR NOMBRE (sin números)
        if (!usuario.getNombre().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new NombreInvalidoException(usuario.getNombre());
        }

        // 🔥 VALIDAR CORREO (formato)
        String regexCorreo = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!Pattern.matches(regexCorreo, usuario.getCorreo())) {
            throw new CorreoInvalidoException(usuario.getCorreo());
        }

        // 🔥 VALIDAR CORREO DUPLICADO
        if (repository.existePorCorreo(usuario.getCorreo())) {
            throw new CorreoDuplicadoException(usuario.getCorreo());
        }

        // ✅ GUARDAR
        return repository.guardar(usuario);
    }
}