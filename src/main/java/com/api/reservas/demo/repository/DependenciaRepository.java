package com.api.reservas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.reservas.demo.classes.Dependencia;
import java.util.List;

public interface DependenciaRepository extends JpaRepository<Dependencia, Long>{
    List<Dependencia> findByDependenciasDisponivelTrue();
}
