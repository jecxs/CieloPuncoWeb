package com.crud.juniorcrud.service;

import com.crud.juniorcrud.entity.Viaje;

import java.util.List;

public interface IViajeService {
    List<Viaje> getAllViajes();
    Viaje getViajeById(Long id);
    Viaje saveViaje(Viaje viaje);
    void deleteViaje(Long id);
}
