package com.crud.juniorcrud.repository;

import com.crud.juniorcrud.entity.Pasajero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Ipasajero extends CrudRepository<Pasajero, Integer> {

}