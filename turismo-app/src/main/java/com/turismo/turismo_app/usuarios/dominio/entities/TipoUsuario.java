package com.turismo.turismo_app.usuarios.dominio.entities;

public enum TipoUsuario {
    CLIENTE,
    GUIA;

    public static TipoUsuario fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("TipoUsuario no puede ser null");
        }

        value = value.trim().toUpperCase();

        if (value.equals("GUIA") || value.equals("GUÍA")) {
            return GUIA;
        }

        try {
            return TipoUsuario.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("TipoUsuario inválido: " + value);
        }
    }
}