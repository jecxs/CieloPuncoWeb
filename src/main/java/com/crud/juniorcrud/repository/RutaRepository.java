package com.crud.juniorcrud.repository;

import com.crud.juniorcrud.entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
    // Métodos de consulta personalizados, si es necesario
}