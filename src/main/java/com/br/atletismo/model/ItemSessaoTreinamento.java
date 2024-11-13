package com.br.atletismo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemSessaoTreinamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercicio_id", nullable = false)
    @JsonIgnore
    private Exercicio exercicio;

    @ManyToOne
    @JoinColumn(name = "sessao_id", nullable = false)
    @JsonIgnore
    private SessaoTreinamento sessao;

    private Double tempo;

    private boolean concluida;
}