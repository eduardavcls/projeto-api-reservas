package com.api.reservas.demo.dto;

import java.time.LocalDateTime;
import com.api.reservas.demo.classes.Funcionarios;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservaDTO {
    @NotNull
    DependenciasDTO dependencia;

    @NotNull
    FuncionariosDTO funcionario;

    @NotNull
    LocalDateTime inicio;

    @NotNull
    LocalDateTime fim;

    Boolean reservaAtiva;

}