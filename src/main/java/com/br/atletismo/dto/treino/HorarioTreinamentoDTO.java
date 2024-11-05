package com.br.atletismo.dto.treino;

import java.time.LocalDate;
import java.util.List;

public record HorarioTreinamentoDTO (int diaSemana, LocalDate dataTreinamento, String descricao, List<ExercicioDTO> exercicioDTOList, Long eventoId) {
}
