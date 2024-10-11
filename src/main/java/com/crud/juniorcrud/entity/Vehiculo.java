package com.crud.juniorcrud.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    @Column(nullable = false)
    private String modelo;

    private String matricula;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaVehiculo categoria;

    public Vehiculo() {
    }

    public Vehiculo(Long id, String marca, String modelo, String matricula, CategoriaVehiculo categoria) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public CategoriaVehiculo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaVehiculo categoria) {
        this.categoria = categoria;
    }
}

enum CategoriaVehiculo {
    BUS, AUTO, CAMIONETA
}