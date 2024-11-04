package com.br.atletismo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@DiscriminatorValue("ATLETA")
@Data
public class Atleta extends Usuario {

    @OneToMany(mappedBy = "atleta")
    private List<SessaoTreinamento> sessoesTreinamento;

    @ManyToMany
    @JoinTable(
            name = "evento_atleta",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Evento> eventos;

}
