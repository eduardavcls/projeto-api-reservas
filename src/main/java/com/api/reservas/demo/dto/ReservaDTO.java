package com.api.reservas.demo.dto;

import java.time.LocalDateTime;
import com.api.reservas.demo.classes.Funcionario;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservaDTO {
    @NotNull
    DependenciaDTO dependencia;

    @NotNull
    FuncionarioDTO funcionario;

    @NotNull
    LocalDateTime inicio;

    @NotNull
    LocalDateTime fim;

    Boolean reservaAtiva;
}