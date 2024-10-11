package com.crud.juniorcrud.service;

import com.crud.juniorcrud.entity.Vehiculo;
import com.crud.juniorcrud.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Optional<Vehiculo> obtenerVehiculoPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

    @Override
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public void eliminarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }
}