package com.api.reservas.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.reservas.demo.classes.Dependencia;
import com.api.reservas.demo.dto.DependenciaDTO;
import com.api.reservas.demo.repository.DependenciaRepository;

@Service
public class DependenciaService {

	@Autowired
	private DependenciaRepository dependenciaRepository;

	public List<Dependencia> getAll() {
		return dependenciaRepository.findAll();
	}

	public Dependencia getById(Long id) {
		return dependenciaRepository.findById(id).orElse(null);
	}

	public List<Dependencia> getAllDisponiveis() {
		return dependenciaRepository.findByDependenciasDisponivelTrue();
	}

	public Dependencia create(Dependencia dependencia) {
		return dependenciaRepository.save(dependencia);
	}

	public void delete(Long id) {
		Dependencia dependencia = getById(id);
		dependenciaRepository.delete(dependencia);

	}

	public DependenciaDTO updateDTO(Long id, DependenciaDTO dependenciaAtualizado) {
		// Obtém a dependência existente pelo ID
		Dependencia dependenciaExistente = dependenciaRepository.findById(id).orElse(null);

		if (dependenciaExistente == null) {
			return null;
		}

		// Atualiza os campos da dependência existente com os valores do DTO
		if (dependenciaAtualizado.getNome() != null) {
			dependenciaExistente.setNome(dependenciaAtualizado.getNome());
		}

		if (dependenciaAtualizado.getCapacidade() != 0) {
			dependenciaExistente.setCapacidade(dependenciaAtualizado.getCapacidade());
		}

		// Salva a dependência atualizada
		Dependencia dependenciaSalvo = dependenciaRepository.save(dependenciaExistente);

		// Cria e retorna o DTO com as informações atualizadas
		DependenciaDTO dependenciaDTO = new DependenciaDTO();
		dependenciaDTO.setId(dependenciaSalvo.getId());
		dependenciaDTO.setNome(dependenciaSalvo.getNome());
		dependenciaDTO.setCapacidade(dependenciaSalvo.getCapacidade());

		return dependenciaDTO;
	}

	private DependenciaDTO converterParaDTO(Dependencia dependencia) {
		DependenciaDTO dto = new DependenciaDTO();
		dto.setId(dependencia.getId());
		dto.setNome(dependencia.getNome());
		dto.setCapacidade(dependencia.getCapacidade());
		return dto;
	}

	private Dependencia converterParaEntidade(DependenciaDTO dependenciaDTO) {
		Dependencia dependencia = new Dependencia();
		dependencia.setId(dependenciaDTO.getId());
		dependencia.setNome(dependenciaDTO.getNome());
		dependencia.setCapacidade(dependenciaDTO.getCapacidade());
		return dependencia;
	}

}
