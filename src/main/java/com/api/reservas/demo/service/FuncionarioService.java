package com.api.reservas.demo.service;

import java.util.List;

import com.api.reservas.demo.dto.FuncionarioDTO;
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

    public FuncionarioDTO create(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = converterParaEntidade(funcionarioDTO);
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        return converterParaDTO(funcionarioSalvo);
    }

    public Funcionario delete(Long id) {
        Funcionario funcionario = getById(id);
        funcionario.setFuncionarioAtivo(false);
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> getAllAtivos() {
        return funcionarioRepository.findByFuncionarioAtivoTrue();
    }

    public FuncionarioDTO update(Long id, FuncionarioDTO funcionarioDTO) {
        Funcionario funcionarioExistente = getById(id);

        if (funcionarioExistente == null) {
            return null;
        }

        if (funcionarioDTO.getNome() != null) {
            funcionarioExistente.setNome(funcionarioDTO.getNome());
        }

        if (funcionarioDTO.getCargo() != null) {
            funcionarioExistente.setCargo(funcionarioDTO.getCargo());
        }

        Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionarioExistente);
        return converterParaDTO(funcionarioAtualizado);
    }

    private FuncionarioDTO converterParaDTO(Funcionario funcionario) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setCargo(funcionario.getCargo());

        return dto;
    }

    private Funcionario converterParaEntidade(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioDTO.getId());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCargo(funcionarioDTO.getCargo());

        return funcionario;
    }

}
