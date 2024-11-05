package com.br.atletismo.service;

import com.br.atletismo.dto.treino.ExercicioDTO;
import com.br.atletismo.dto.treino.HorarioTreinamentoDTO;
import com.br.atletismo.model.Evento;
import com.br.atletismo.model.Exercicio;
import com.br.atletismo.model.HorarioTreinamento;
import com.br.atletismo.repository.ExercicioRepository;
import com.br.atletismo.repository.HorarioTreinamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreinoService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private HorarioTreinamentoRepository horarioTreinamentoRepository;

    @Autowired
    private EventoService eventoService;

    @Transactional
    public void salvarTreino(List<HorarioTreinamentoDTO> horarioTreinamentoDTOList) {
        // Obter o evento associado ao primeiro horário de treinamento
        Evento eventoTreino = eventoService.findById(horarioTreinamentoDTOList.get(0).eventoId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        for (HorarioTreinamentoDTO horarioTreinamentoDTO : horarioTreinamentoDTOList) {
            // Criar uma lista para os exercícios associados ao horário
            List<Exercicio> exerciciosList = new ArrayList<>();

            for (ExercicioDTO exercicioDTO : horarioTreinamentoDTO.exercicioDTOList()) {
                // Criar um novo exercício e associá-lo ao horário de treinamento
                Exercicio exercicio = new Exercicio(
                        null,
                        exercicioDTO.indice(),
                        exercicioDTO.descricao(),
                        exercicioDTO.tipo(),
                        null); // Inicialmente, o horário será associado após salvar o horário

                // Adicionar o exercício à lista
                exerciciosList.add(exercicio);
            }

            // Criar o objeto HorarioTreinamento e associar os exercícios
            HorarioTreinamento horarioTreinamento = new HorarioTreinamento(
                    null,
                    horarioTreinamentoDTO.diaSemana(),
                    horarioTreinamentoDTO.dataTreinamento(),
                    horarioTreinamentoDTO.descricao(),
                    eventoTreino,
                    exerciciosList);

            HorarioTreinamento horarioTreinamentoRetorno = horarioTreinamentoRepository.save(horarioTreinamento);
            for (Exercicio exercicio : exerciciosList) {
                exercicio.setHorario(horarioTreinamentoRetorno);
                exercicioRepository.save(exercicio);
            }
        }
    }

}
