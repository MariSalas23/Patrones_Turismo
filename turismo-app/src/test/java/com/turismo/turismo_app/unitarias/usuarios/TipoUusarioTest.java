package com.turismo.turismo_app.unitarias.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.turismo.turismo_app.usuarios.dominio.entities.TipoUsuario;

class TipoUsuarioTest {

    @Test
    void deberiaConvertirClienteEnMayusculas() {
        assertEquals(TipoUsuario.CLIENTE, TipoUsuario.fromString("CLIENTE"));
    }

    @Test
    void deberiaConvertirClienteEnMinusculas() {
        assertEquals(TipoUsuario.CLIENTE, TipoUsuario.fromString("cliente"));
    }

    @Test
    void deberiaConvertirGuiaConEspacios() {
        assertEquals(TipoUsuario.GUIA, TipoUsuario.fromString("  GUIA  "));
    }

    @Test
    void deberiaAceptarGuiaConTilde() {
        assertEquals(TipoUsuario.GUIA, TipoUsuario.fromString("GUÍA"));
    }

    @Test
    void deberiaLanzarExcepcionCuandoEsNull() {
        assertThrows(IllegalArgumentException.class, () -> TipoUsuario.fromString(null));
    }

    @Test
    void deberiaLanzarExcepcionCuandoElTipoEsInvalido() {
        assertThrows(IllegalArgumentException.class, () -> TipoUsuario.fromString("ADMIN"));
    }

    @Test
    void deberiaLanzarExcepcionCuandoElTextoEstaVacio() {
        assertThrows(IllegalArgumentException.class, () -> TipoUsuario.fromString(""));
    }

    @Test
    void deberiaLanzarExcepcionCuandoSoloHayEspacios() {
        assertThrows(IllegalArgumentException.class, () -> TipoUsuario.fromString("   "));
    }
}