package com.br.atletismo.model;

import com.br.atletismo.model.enums.TipoExercicio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int indice;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoExercicio tipo;

    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private HorarioTreinamento horario;

}
