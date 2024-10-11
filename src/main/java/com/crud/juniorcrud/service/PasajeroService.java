package com.crud.juniorcrud.service;

import com.crud.juniorcrud.entity.Pasajero;
import com.crud.juniorcrud.repository.Ipasajero;
import com.crud.juniorcrud.repository.PasajeroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PasajeroService implements IpasajeroService {

    @Autowired
    private Ipasajero potito;
    @Autowired
    private PasajeroRepository pasajeroRepository;

    @Override
    public List<Pasajero> listar() {
        return (List<Pasajero>) potito.findAll();
    }

    @Override
    public Optional<Pasajero> listarId(int id) {
        return potito.findById(id);
    }

    @Override
    public int save(Pasajero p) {
        int res=0;
        Pasajero pasajero =potito.save(p);
        if(!pasajero.equals(null)){
            res=1;
        }
        return res;
    }

    @Override
    public void delete(int id) {
        potito.deleteById(id);
    }
    @Override
    public List<Pasajero> buscarPasajeros(String keyword) {
        return pasajeroRepository.findByNombreContainingOrApellidoContaining(keyword, keyword);
    }
}


