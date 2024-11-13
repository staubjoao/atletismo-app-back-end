package com.br.atletismo.dto;

import com.br.atletismo.model.Evento;
import com.br.atletismo.model.Exercicio;

import java.time.LocalDate;
import java.util.List;

public record HorarioTreinamentoRetornoDTO(Long id, LocalDate dataTreinamento, int diaSemana, Evento evento, List<Exercicio> exercicios, boolean concluido) {
}
