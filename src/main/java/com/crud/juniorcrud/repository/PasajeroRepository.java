package com.crud.juniorcrud.repository;

import com.crud.juniorcrud.entity.Pasajero; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Integer> {
	List<Pasajero> findByNombreContainingOrApellidoContaining(String nombre, String apellido);
}