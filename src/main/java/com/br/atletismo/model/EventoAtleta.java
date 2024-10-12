package com.br.atletismo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class EventoAtleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "atleta_id", nullable = false)
    private Atleta atleta;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Column(unique = true)
    private String relacaoUnica; // Substitui o @@unique do Prisma
}
