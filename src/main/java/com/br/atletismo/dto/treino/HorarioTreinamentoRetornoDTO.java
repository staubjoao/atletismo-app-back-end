package com.br.atletismo.dto.treino;

import com.br.atletismo.model.Evento;

import java.time.LocalDate;
import java.util.List;

public record HorarioTreinamentoRetornoDTO (String diaSemana, LocalDate dataTreinamento, String descricao, List<ExercicioDTO> exercicioDTOList, Evento evento) {
}
