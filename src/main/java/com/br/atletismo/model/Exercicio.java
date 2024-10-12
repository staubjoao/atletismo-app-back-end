package com.br.atletismo.model;

import com.br.atletismo.model.enums.TipoExercicio;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int indice;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoExercicio tipo;

    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private HorarioTreinamento horario;

    @OneToMany(mappedBy = "exercicio")
    private List<ItemSessaoTreinamento> itensSessao;
}
