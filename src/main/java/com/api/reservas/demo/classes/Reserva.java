package com.api.reservas.demo.classes;

import com.api.reservas.demo.classes.Dependencia;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dependencias_id", referencedColumnName = "id")
    private Dependencia dependencia;

    @OneToOne
    @JoinColumn(name = "funcionarios_id", referencedColumnName = "id")
    private Funcionario funcionario;

    @Column(name = "horario_inicio")
    private LocalDateTime inicio;

    @Column(name = "horario_fim")
    private LocalDateTime fim;

    @Column(name = "reserva_ativa")
    private Boolean reservaAtiva = true;
}