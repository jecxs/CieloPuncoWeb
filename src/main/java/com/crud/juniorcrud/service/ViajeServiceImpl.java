package com.crud.juniorcrud.service;

import com.crud.juniorcrud.entity.Viaje;
import com.crud.juniorcrud.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ViajeServiceImpl implements IViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Override
    public List<Viaje> getAllViajes() {
        return viajeRepository.findAll();
    }

    @Override
    public Viaje getViajeById(Long id) {
        return viajeRepository.findById(id).orElse(null);
    }

    @Override
    public Viaje saveViaje(Viaje viaje) {
        // Calcular el precio total antes de guardar
        viaje.setPrecioTotal(viaje.calcularPrecioTotal());
        return viajeRepository.save(viaje);
    }

    @Override
    public void deleteViaje(Long id) {
        viajeRepository.deleteById(id);
    }
}