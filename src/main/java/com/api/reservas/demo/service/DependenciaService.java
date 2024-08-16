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

	public DependenciaDTO create(DependenciaDTO dependenciaDTO) {
		Dependencia dependencia = converterParaEntidade(dependenciaDTO);
		Dependencia dependenciaSalva = dependenciaRepository.save(dependencia);
		return converterParaDTO(dependenciaSalva);
	}

	public void delete(Long id) {
		Dependencia dependencia = getById(id);
		dependenciaRepository.delete(dependencia);
	}

	public DependenciaDTO update(Long id, DependenciaDTO dependenciaAtualizado) {
		// Obtém a dependência existente pelo ID
		Dependencia dependenciaExistente = getById(id);

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
		Dependencia dependenciaSalva = dependenciaRepository.save(dependenciaExistente);
		return converterParaDTO(dependenciaSalva);

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
