package com.turismo.turismo_app.usuarios.dominio.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    private String nombre;
    private String correo;
    private TipoUsuario tipo;

    public Usuario() {}

    public Usuario(String nombre, String correo, TipoUsuario tipo) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public TipoUsuario getTipo() { return tipo; }

    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
}