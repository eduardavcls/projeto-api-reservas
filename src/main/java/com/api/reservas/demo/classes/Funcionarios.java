package com.api.reservas.demo.classes;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "funcionarios")

public class Funcionarios {

@Column(nullable = false)
private String nome;

@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(nullable = false)
private String cargo;

@Column(nullable = false)
private boolean funcionarioAtivo = true;
}