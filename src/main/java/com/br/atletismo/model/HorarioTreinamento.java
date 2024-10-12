package com.br.atletismo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class HorarioTreinamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int diaSemana;
    private LocalDateTime horaInicio;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @OneToMany(mappedBy = "horario")
    private List<SessaoTreinamento> sessoes;

    @OneToMany(mappedBy = "horario")
    private List<Exercicio> exercicios;
}
