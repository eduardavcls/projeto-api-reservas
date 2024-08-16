package com.api.reservas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.reservas.demo.classes.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {


}
