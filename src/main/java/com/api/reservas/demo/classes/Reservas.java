package com.api.reservas.demo.classes;
import com.api.reservas.demo.classes.Dependencias;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "reservas")
public class Reservas {

 @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;

 @OneToOne
 @JoinColumn(name = "dependencias_id", referencedColumnName = "id")
 private Dependencias dependencias;

 @ManyToOne
 @JoinColumn(name = "funcionarios_id", referencedColumnName = "id")
 private Funcionarios funcionarios;

 @Column(name = "horario_inicio")
 private LocalDateTime inicio;

 @Column(name = "horario_fim")
 private LocalDateTime fim;


}
