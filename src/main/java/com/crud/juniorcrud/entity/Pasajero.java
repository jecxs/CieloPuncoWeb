package com.crud.juniorcrud.entity;

import jakarta.persistence.*;

@Entity
@Table(name="pasajero")
public class Pasajero {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String dni;
    private String telefono;


    public Pasajero() {}

    public Pasajero(String nombre, int id, String apellido, int edad, String dni, String telefono) {
        this.nombre = nombre;
        this.id = id;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}

