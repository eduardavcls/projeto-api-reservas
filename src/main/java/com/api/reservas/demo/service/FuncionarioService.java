package com.api.reservas.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.reservas.demo.classes.Dependencia;
import com.api.reservas.demo.classes.Funcionario;
import com.api.reservas.demo.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> getAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario getById(Long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    public Funcionario create(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);

    }

    public Funcionario delete(Long id) {
        Funcionario funcionario = getById(id);
        funcionario.setFuncionarioAtivo(false);
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> getAllAtivos() {
        return funcionarioRepository.findByFuncionarioAtivoTrue();
    }

}
