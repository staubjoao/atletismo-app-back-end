package com.br.atletismo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("ATLETA")
@Data
@NoArgsConstructor
public class Atleta extends Usuario {

    @OneToMany(mappedBy = "atleta")
    private List<SessaoTreinamento> sessoesTreinamento;

    @ManyToMany
    @JoinTable(
            name = "evento_atleta",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    @JsonIgnore
    private List<Evento> eventos;

    public Atleta(Usuario usuario) {
        this.setId(usuario.getId());
        this.setNome(usuario.getNome());
        this.setEmail(usuario.getEmail());
        this.setSenha(usuario.getSenha());
        this.setFuncao(usuario.getFuncao());
        this.setClubes(usuario.getClubes());
    }
}
