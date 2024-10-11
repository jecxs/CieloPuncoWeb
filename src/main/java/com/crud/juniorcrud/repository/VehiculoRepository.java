package com.crud.juniorcrud.repository;

import com.crud.juniorcrud.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}