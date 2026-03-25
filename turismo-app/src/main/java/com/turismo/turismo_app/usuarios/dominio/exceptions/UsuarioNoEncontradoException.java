package com.turismo.turismo_app.usuarios.dominio.exceptions;

public class UsuarioNoEncontradoException extends UsuarioException {

    public UsuarioNoEncontradoException(String id) {
        super("Usuario no encontrado", "id", id);
    }
}