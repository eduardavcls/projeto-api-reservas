package com.api.reservas.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.api.reservas.demo.classes.Dependencias;
import com.api.reservas.demo.classes.Funcionarios;
import com.api.reservas.demo.classes.Reservas;
import com.api.reservas.demo.dto.ReservaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import com.api.reservas.demo.repository.DependenciasRepository;
import com.api.reservas.demo.repository.ReservasRepository;

@Service
public class ReservasService {

    @Autowired
    private ReservasRepository reservasRepository;

    @Autowired
    private DependenciasRepository dependenciasRepository;

    @Autowired
    private DependenciasService dependenciasService;

    @Autowired
    private FuncionariosService funcionariosService;

    public List<Reservas> getAll() {
        return reservasRepository.findAll();
    }

    public Reservas getById(Long id) {
        return reservasRepository.findById(id)
                .orElse(null);
    }

    public ReservaDTO create(ReservaDTO reservaDTO) {
        Reservas reserva = new Reservas();
        reserva.setDependencias(dependenciasService.getById(reservaDTO.getDependencia().getId()));
        reserva.setFuncionarios(funcionariosService.getById(reservaDTO.getFuncionario().getId()));
        reserva.setInicio(reserva.getInicio());
        reserva.setFim(reservaDTO.getFim());
        reserva.setReservaAtiva(true);

        Reservas reservaSalva = reservasRepository.save(reserva);
        
        ReservaDTO reservaSalvaDTO = new ReservaDTO();
        reservaSalvaDTO.getDependencia().setNome(reservaSalva.getDependencias().getNome());
        reservaSalvaDTO.getFuncionario().setNome(reservaSalva.getFuncionarios().getNome());
        reservaSalvaDTO.setInicio(reservaSalva.getInicio());
        reservaSalvaDTO.setFim(reservaSalva.getFim());

        return reservaSalvaDTO;
    }

    public Reservas delete(Long id) {
        Reservas reserva = getById(id);
        reserva.setReservaAtiva(false);

        Dependencias dependencias = reserva.getDependencias();

        if (dependencias != null) {
            dependencias.setDependenciasDisponivel(true);
            dependenciasRepository.save(dependencias);
        }
        return reservasRepository.save(reserva);
    }

}
