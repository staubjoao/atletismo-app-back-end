package com.br.atletismo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@DiscriminatorValue("ATLETA")
@Data
public class Atleta extends Usuario {

    @OneToMany(mappedBy = "atleta")
    private List<SessaoTreinamento> sessoesTreinamento;

    @OneToMany(mappedBy = "atleta")
    private List<EventoAtleta> eventosAtleta;

}
