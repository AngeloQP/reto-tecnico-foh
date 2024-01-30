package com.reto.backend.repository;

import com.reto.backend.entities.DetalleVenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends CrudRepository<DetalleVenta, Long> {
}
