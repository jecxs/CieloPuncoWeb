package com.crud.juniorcrud.service;

import com.crud.juniorcrud.entity.Ruta;
import com.crud.juniorcrud.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutaServiceImpl implements IRutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Override
    public Ruta guardarRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    @Override
    public Ruta obtenerRutaPorId(Long id) {
        return rutaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ruta> listarRutas() {
        return rutaRepository.findAll();
    }

    @Override
    public Ruta actualizarRuta(Long id, Ruta ruta) {
        if (rutaRepository.existsById(id)) {
            ruta.setId(id);
            return rutaRepository.save(ruta);
        }
        return null;
    }

    @Override
    public void eliminarRuta(Long id) {
        rutaRepository.deleteById(id);
    }
}