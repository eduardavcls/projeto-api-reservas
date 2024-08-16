package com.api.reservas.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FuncionariosDTO {
    @NotNull(message = "O id é obrigatório...")
    private Long id;
    private String nome;
    private String cargo;
}