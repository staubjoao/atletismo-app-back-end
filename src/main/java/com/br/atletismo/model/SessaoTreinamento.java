package com.br.atletismo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class SessaoTreinamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime data;
    private boolean completada;
    private String feedback;

    @ManyToOne
    @JoinColumn(name = "atleta_id", nullable = false)
    private Atleta atleta;

    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private HorarioTreinamento horario;

    @OneToMany(mappedBy = "sessao")
    private List<ItemSessaoTreinamento> itens;
}