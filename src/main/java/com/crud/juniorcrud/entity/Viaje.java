package com.crud.juniorcrud.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="viaje")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta ruta;

    @ManyToOne
    @JoinColumn(name = "pasajero_id", nullable = false)
    private Pasajero pasajero;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    private LocalDateTime fechaViaje;

    private Double precioTotal;

    public Viaje() {
    }

    public Viaje(Long id, Ruta ruta, Pasajero pasajero, Vehiculo vehiculo, LocalDateTime fechaViaje, Double precioTotal) {
        this.id = id;
        this.ruta = ruta;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaViaje = fechaViaje;
        this.precioTotal = precioTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDateTime fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Double calcularPrecioTotal() {
        double precioRuta = ruta.getPrecio();
        double precioAdicional = 0;

        switch (vehiculo.getCategoria()) {
            case AUTO:
                precioAdicional = 20;
                break;
            case CAMIONETA:
                precioAdicional = 30;
                break;
            case BUS:
            default:
                break;
        }

        return precioRuta + precioAdicional;
    }
}