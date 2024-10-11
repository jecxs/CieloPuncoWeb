package com.crud.juniorcrud.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String origen;
    private String destino;
    private String duracion;
    private Double distancia;
    private Double precio;

    public Ruta() {
    }

    public Ruta(Long id, String nombre, String destino, String origen, String duracion, Double distancia, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.destino = destino;
        this.origen = origen;
        this.duracion = duracion;
        this.distancia = distancia;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}