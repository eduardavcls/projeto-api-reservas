package com.api.reservas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.reservas.demo.classes.Funcionario;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
  List<Funcionario> findByFuncionarioAtivoTrue();
}
