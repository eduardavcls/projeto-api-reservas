package com.api.reservas.demo.dto;

import lombok.Data;

@Data
public class DependenciaDTO {
    private Long id;
    private String nome;
    private int capacidade;
}