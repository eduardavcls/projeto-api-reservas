package com.api.reservas.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.reservas.demo.classes.Dependencias;
import com.api.reservas.demo.dto.DependenciasUpdateDTO;
import com.api.reservas.demo.dto.DependenciasDTO;
import com.api.reservas.demo.repository.DependenciasRepository;
import java.util.ArrayList;

@Service
public class DependenciasService {

	@Autowired
	private DependenciasRepository dependenciasRepository;

	public List<Dependencias> getAll(){
		return dependenciasRepository.findAll();
	}

	public Dependencias getById(Long id) {
		return dependenciasRepository.findById(id)
										.orElse(null);
	}

	public List<Dependencias> getAllDisponiveis() {
		return dependenciasRepository.findByDependenciasDisponivelTrue();
	}

	public Dependencias create(Dependencias dependencias) {
		return dependenciasRepository.save(dependencias);
	}

	public void delete(Long id) {
		Dependencias dependencias = getById(id);
		dependenciasRepository.delete(dependencias);
		
	}

	public Dependencias update(Long id, Dependencias dependencias){
		Dependencias dependenciasExistente = getById(id);

		if (dependenciasExistente == null) {
			return null;
		}

		dependenciasExistente.setNome(dependencias.getNome());
		dependenciasExistente.setCapacidade(dependencias.getCapacidade());
		
		return create(dependenciasExistente);
	}

	public DependenciasDTO updateDTO(Dependencias dependenciaExistente, DependenciasUpdateDTO dependenciasAtualizado ) {
		
		if(dependenciasAtualizado.getNome() != null){
			dependenciaExistente.setNome(dependenciasAtualizado.getNome());
		}
		if ( dependenciasAtualizado.getCapacidade() != 0 ) {
			dependenciaExistente.setCapacidade(dependenciasAtualizado.getCapacidade());
		}

	  Dependencias dependenciaSalvo = dependenciasRepository.save(dependenciaExistente);
	  
	  DependenciasDTO dependenciaDTO = new DependenciasDTO();
	  dependenciaDTO.setNome(dependenciaSalvo.getNome());
	  dependenciaDTO.setCapacidade(dependenciaSalvo.getCapacidade());

	   return dependenciaDTO;
	}

/*public List<DependenciasDTO> getDependenciasDTO() {

        List<DependenciasDTO> dependenciasDTO = dependenciasRepository.findAll();

        List<DependenciasDTO> dependenciasDTO = new ArrayList<>();

        for (Dependencias dependencias : dependencias) {
            DependenciasDTO dependenciasDTO = new DependenciasDTO();
            dependenciasDTO.setId(dependencias.getId());
            dependenciasDTO.setNome(dependencias.getNome());

            dependenciasDTO.add(dependenciasDTO);
        }
        return dependenciasDTO;
    }*/
}

