package com.br.atletismo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Clube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, length = 5)
    private String codigo;

    @ManyToMany(mappedBy = "clubes")
    private List<Usuario> usuarios;

    public Clube() {
        this.usuarios = new ArrayList<>();
    }

    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

}
