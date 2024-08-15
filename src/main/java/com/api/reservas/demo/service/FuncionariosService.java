package com.api.reservas.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.reservas.demo.classes.Dependencias;
import com.api.reservas.demo.classes.Funcionarios;
import com.api.reservas.demo.repository.FuncionariosRepository;

@Service
public class FuncionariosService {

    @Autowired
     private FuncionariosRepository funcionariosRepository;

	public List<Funcionarios> getAll() {
		return funcionariosRepository.findAll();
	}

    public Funcionarios getById(Long id) {
        return funcionariosRepository.findById(id)
										.orElse(null);
    }
    
	public Funcionarios create(Funcionarios funcionarios) {
		return funcionariosRepository.save(funcionarios);
	
	}

	public Funcionarios delete(Long id) {
        Funcionarios funcionarios = getById(id);
        funcionarios.setFuncionarioAtivo(false);
        return funcionariosRepository.save(funcionarios);
	}

    public List<Funcionarios> getAllAtivos() {
        return funcionariosRepository.findByFuncionariosAtivoTrue();
    }

}
