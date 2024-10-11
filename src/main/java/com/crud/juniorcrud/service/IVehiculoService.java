package com.crud.juniorcrud.service;

import com.crud.juniorcrud.entity.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface IVehiculoService {
    List<Vehiculo> obtenerTodosLosVehiculos();
    Optional<Vehiculo> obtenerVehiculoPorId(Long id);
    Vehiculo guardarVehiculo(Vehiculo vehiculo);
    void eliminarVehiculo(Long id);
}