package com.br.atletismo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class SessaoTreinamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;
    private String feedback;
    private boolean concluida;

    @ManyToOne
    @JoinColumn(name = "atleta_id", nullable = false)
    @JsonIgnore
    private Atleta atleta;

    @ManyToOne
    private HorarioTreinamento horarioTreinamento;

    @OneToMany(mappedBy = "sessao")
    @JsonIgnore
    private List<ItemSessaoTreinamento> itemSessaoTreinamentosList;
}