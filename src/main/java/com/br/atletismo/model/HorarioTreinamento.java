package com.br.atletismo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class HorarioTreinamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int diaSemana;
    private LocalDate horaInicio;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @OneToMany(mappedBy = "horario")
    private List<Exercicio> exercicios;
}
