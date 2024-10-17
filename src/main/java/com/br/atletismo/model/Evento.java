package com.br.atletismo.model;

import com.br.atletismo.model.enums.TipoEvento;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoEvento tipo;

    @ManyToOne
    @JoinColumn(name = "clube_id", nullable = false)
    private Clube clube;

    @OneToMany(mappedBy = "evento")
    private List<HorarioTreinamento> horarios;

    @OneToMany(mappedBy = "evento")
    private List<EventoAtleta> eventosAtleta;
}
