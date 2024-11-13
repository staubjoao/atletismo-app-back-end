package com.br.atletismo.model;

import com.br.atletismo.model.enums.TipoEvento;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany(mappedBy = "eventos")
    @JsonIgnore
    private List<Atleta> atletas;

}
