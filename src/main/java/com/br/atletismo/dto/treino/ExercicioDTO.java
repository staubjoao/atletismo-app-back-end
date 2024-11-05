package com.br.atletismo.dto.treino;

import com.br.atletismo.model.enums.TipoExercicio;

public record ExercicioDTO(int indice, String descricao, TipoExercicio tipo) {
}
