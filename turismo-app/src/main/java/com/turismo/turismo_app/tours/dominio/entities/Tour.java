package com.turismo.turismo_app.tours.dominio.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tours")
public class Tour {

    @Id
    private String id;

    private String nombre;
    private String ubicacion;
    private String guiaId;

    private int capacidadMaxima;
    private int cuposDisponibles;

    private double precio;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    private boolean activo;

    public Tour() {}

    public Tour(String nombre, String ubicacion, String guiaId,
                int capacidadMaxima, double precio,
                LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.guiaId = guiaId;
        this.capacidadMaxima = capacidadMaxima;
        this.cuposDisponibles = capacidadMaxima;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = true;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public String getGuiaId() { return guiaId; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public int getCuposDisponibles() { return cuposDisponibles; }
    public double getPrecio() { return precio; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public LocalDateTime getFechaFin() { return fechaFin; }
    public boolean isActivo() { return activo; }

    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setGuiaId(String guiaId) { this.guiaId = guiaId; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }
    public void setCuposDisponibles(int cuposDisponibles) { this.cuposDisponibles = cuposDisponibles; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }
    public void setActivo(boolean activo) { this.activo = activo; }
}