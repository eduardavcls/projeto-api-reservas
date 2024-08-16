package com.api.reservas.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.api.reservas.demo.classes.Dependencia;
import com.api.reservas.demo.classes.Funcionario;
import com.api.reservas.demo.classes.Reserva;
import com.api.reservas.demo.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.reservas.demo.repository.DependenciaRepository;
import com.api.reservas.demo.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private DependenciaRepository dependenciaRepository;

    @Autowired
    private DependenciaService dependenciaService;

    @Autowired
    private FuncionarioService funcionarioService;

    public List<Reserva> getAll() {
        return reservaRepository.findAll();
    }

    public Reserva getById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public ReservaDTO create(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        reserva.setDependencia(dependenciaService.getById(reservaDTO.getDependencia().getId()));
        reserva.setFuncionario(funcionarioService.getById(reservaDTO.getFuncionario().getId()));
        reserva.setInicio(reserva.getInicio());
        reserva.setFim(reservaDTO.getFim());
        reserva.setReservaAtiva(true);

        Reserva reservaSalva = reservaRepository.save(reserva);
        
        ReservaDTO reservaSalvaDTO = new ReservaDTO();
        reservaSalvaDTO.getDependencia().setNome(reservaSalva.getDependencia().getNome());
        reservaSalvaDTO.getFuncionario().setNome(reservaSalva.getFuncionario().getNome());
        reservaSalvaDTO.setInicio(reservaSalva.getInicio());
        reservaSalvaDTO.setFim(reservaSalva.getFim());

        return reservaSalvaDTO;
    }

    public Reserva delete(Long id) {
        Reserva reserva = getById(id);
        reserva.setReservaAtiva(false);
        return reservaRepository.save(reserva);
    }

}
