package com.turismo.turismo_app.usuarios.dominio.exceptions;

public class NombreInvalidoException extends UsuarioException {
    public NombreInvalidoException(String nombre) {
        super("El nombre contiene caracteres inválidos", "nombre", nombre);
    }
}