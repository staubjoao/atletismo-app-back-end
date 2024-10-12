package com.br.atletismo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemSessaoTreinamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(name = "sessao_id", nullable = false)
    private SessaoTreinamento sessao;

    private String feedback;
}