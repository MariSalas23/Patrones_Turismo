package com.turismo.turismo_app.usuarios.dominio.entities;

public enum TipoUsuario {
    CLIENTE,
    GUIA;

    public static TipoUsuario fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("TipoUsuario no puede ser null o vacío");
        }

        value = value.trim().toUpperCase();

        if (value.equals("GUIA") || value.equals("GUÍA")) {
            return GUIA;
        }

        return TipoUsuario.valueOf(value);
    }
}