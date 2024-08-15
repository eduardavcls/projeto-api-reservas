package com.api.reservas.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.api.reservas.demo.classes.Dependencias;
import com.api.reservas.demo.classes.Reservas;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.reservas.demo.repository.DependenciasRepository;
import com.api.reservas.demo.repository.ReservasRepository;

@Service
public class ReservasService {

    @Autowired
    private ReservasRepository reservasRepository;

    @Autowired
    private DependenciasRepository dependenciasRepository;

	public List<Reservas> getAll() {
		return reservasRepository.findAll();
	}

    public Reservas getById(Long id) {
        return reservasRepository.findById(id)
									.orElse(null);
    }

    public Reservas create(Reservas reservas) {
        // Verifica se a dependência está dísponivel
        Dependencias dependencias = reservas.getDependencias();
        if (dependencias != null && dependencias.isDisponivel()) {
            // Define a dependência como indisponível
            dependencias.setDisponivel(false);

            // Salva a dependência atualizada
            dependenciasRepository.save(dependencias);

            // Salva e retorna a reserva
            return reservasRepository.save(reservas);
        } else {
            throw new IllegalStateException("Dependência não esta disponivel");
        }
    }

    public Reservas delete(Long id){
        Reservas reserva = getById(id);
        reserva.setReserva_ativa(false);

        Dependencias dependencias = reserva.getDependencias();

        if (dependencias != null) {
            dependencias.setDisponivel(true);
            dependenciasRepository.save(dependencias);
        }
        return reservasRepository.save(reserva);
    }

}
