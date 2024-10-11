package com.crud.juniorcrud.service;


import com.crud.juniorcrud.entity.Ruta;

import java.util.List;

public interface IRutaService {
    Ruta guardarRuta(Ruta ruta);
    Ruta obtenerRutaPorId(Long id);
    List<Ruta> listarRutas();
    Ruta actualizarRuta(Long id, Ruta ruta);
    void eliminarRuta(Long id);
}