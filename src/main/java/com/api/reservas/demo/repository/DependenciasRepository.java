package com.api.reservas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.reservas.demo.classes.Dependencias;
import java.util.List;

public interface DependenciasRepository extends JpaRepository<Dependencias, Long>{
    List<Dependencias> findByDependenciasDisponivelTrue();
}
