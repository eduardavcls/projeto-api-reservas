package com.api.reservas.demo.classes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "dependencias")

public class Dependencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int capacidade;

    @Column(name = "dependencia_disponivel")
    private Boolean dependenciasDisponivel = true;
}
