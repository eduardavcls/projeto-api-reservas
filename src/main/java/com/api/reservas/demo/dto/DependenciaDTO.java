package com.api.reservas.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
public class DependenciaDTO {
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Positive(message = "Capacidade deve ser positiva")
    private Integer capacidade;
}