package com.api.reservas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.reservas.demo.classes.Funcionarios;
import java.util.List;

public interface FuncionariosRepository extends JpaRepository<Funcionarios, Long> {
  List<Funcionarios> findById(long id);
  List<Funcionarios> findByFuncionarioAtivoTrue();
}
