package com.api.reservas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.reservas.demo.classes.Reservas;

public interface ReservasRepository extends JpaRepository<Reservas, Long> {


}
