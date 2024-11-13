package com.br.atletismo.dto.treinamento;

import java.time.LocalDateTime;
import java.util.List;

public record TreinamentoDTO(Long idHorarioTreinamento, LocalDateTime data, String feedback, List<ExercicioTreinamentoDTO> exercicios) {
}
