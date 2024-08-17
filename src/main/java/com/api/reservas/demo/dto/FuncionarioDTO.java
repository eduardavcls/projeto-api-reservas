package com.api.reservas.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FuncionarioDTO {
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "Cargo não pode ser vazio")
    @Size(max = 50, message = "O cargo deve ter no máximo 50 caracteres")
    private String cargo;
}