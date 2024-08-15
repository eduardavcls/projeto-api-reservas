package com.api.reservas.demo.dto;

import lombok.Data;

@Data
public class DependenciasUpdateDTO {
    private Long id;
    private String nome;
    private int capacidade;
}
