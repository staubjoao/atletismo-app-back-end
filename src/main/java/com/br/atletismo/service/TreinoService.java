package com.br.atletismo.service;

import com.br.atletismo.dto.HorarioTreinamentoRetornoDTO;
import com.br.atletismo.dto.treino.ExercicioDTO;
import com.br.atletismo.dto.treino.HorarioTreinamentoDTO;
import com.br.atletismo.model.*;
import com.br.atletismo.repository.*;
import com.br.atletismo.security.AuthenticatedUserUtil;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClubeRepository clubeRepository;

    @Autowired
    private EventoRepository eventoRepository;

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
                    eventoTreino,
                    null);

            HorarioTreinamento horarioTreinamentoRetorno = horarioTreinamentoRepository.save(horarioTreinamento);
            for (Exercicio exercicio : exerciciosList) {
                exercicio.setHorario(horarioTreinamentoRetorno);
                exercicioRepository.save(exercicio);
            }
        }
    }

    public List<HorarioTreinamento> getAllHorarioTreinamento() {
        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        return horarioTreinamentoRepository.findHorariosWithExerciciosByUsuarioEmail(email);
    }

    public List<HorarioTreinamentoRetornoDTO> getByEventoHorarioTreinamento(Long eventoId) {
        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        List<HorarioTreinamento> todosHorariosTreinamento =
                horarioTreinamentoRepository.findHorariosWithExerciciosByUsuarioEmailAndEvento(email, eventoId);

        List<HorarioTreinamento> horariosTreinamentoNaoRalizados =
                horarioTreinamentoRepository.findHorariosWithoutSessaoByUsuarioEmailAndEvento(email, eventoId);

        return todosHorariosTreinamento.stream()
                .map(horario -> {
                    boolean concluido = !horariosTreinamentoNaoRalizados.contains(horario);
                    return new HorarioTreinamentoRetornoDTO(
                            horario.getId(),
                            horario.getDataTreinamento(),
                            horario.getDiaSemana(),
                            horario.getEvento(),
                            horario.getExercicios(),
                            concluido
                    );
                })
                .toList();
    }

}
