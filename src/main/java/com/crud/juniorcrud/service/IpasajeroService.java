package com.crud.juniorcrud.service;

import com.crud.juniorcrud.entity.Pasajero;

import java.util.List;
import java.util.Optional;

public interface   IpasajeroService {

    public List<Pasajero> listar();
    public Optional<Pasajero> listarId(int id);
    public int save(Pasajero pasajero);
    public void delete(int id);
    List<Pasajero> buscarPasajeros(String keyword);
}
