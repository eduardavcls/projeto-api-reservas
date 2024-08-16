package com.api.reservas.demo.dto;

import java.time.LocalDateTime;
import com.api.reservas.demo.classes.Funcionario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservaDTO {
    @NotNull(message = "O id é obrigatório")
    private Long id;

    @NotNull(message = "Dependência não pode ser nula")
    private DependenciaDTO dependencia;

    @NotNull(message = "Funcionário não pode ser nulo")
    private FuncionarioDTO funcionario;

    @NotNull(message = "Data de início não pode ser nula")
    @Future(message = "Data de início deve ser no futuro")
    private LocalDateTime inicio;

    @NotNull(message = "Data de fim não pode ser nula")
    @Future(message = "Data de fim deve ser no futuro")
    private LocalDateTime fim;

    Boolean reservaAtiva;
}