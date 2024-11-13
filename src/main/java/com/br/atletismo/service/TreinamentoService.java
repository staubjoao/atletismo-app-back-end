package com.br.atletismo.service;

import com.br.atletismo.dto.treinamento.ExercicioTreinamentoDTO;
import com.br.atletismo.dto.treinamento.TreinamentoDTO;
import com.br.atletismo.model.*;
import com.br.atletismo.repository.ItemSessaoTreinamentoRepository;
import com.br.atletismo.repository.SessaoTreinamentoRepository;
import com.br.atletismo.repository.UsuarioRepository;
import com.br.atletismo.security.AuthenticatedUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreinamentoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SessaoTreinamentoRepository sessaoTreinamentoRepository;

    @Autowired
    private ExercicioService exercicioService;

    @Autowired
    private ItemSessaoTreinamentoService itemSessaoTreinamentoService;

    @Autowired
    private HorarioTreinamentoService horarioTreinamentoService;

    public void salvarTreinamento(TreinamentoDTO treinamentoDTO) {
        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Atleta atleta = new Atleta(usuario);

        HorarioTreinamento horarioTreinamento = horarioTreinamentoService.findById(treinamentoDTO.idHorarioTreinamento()).get();

        SessaoTreinamento sessaoTreinamento = new SessaoTreinamento();
        sessaoTreinamento.setAtleta(atleta);
        sessaoTreinamento.setFeedback(treinamentoDTO.feedback());
        sessaoTreinamento.setData(treinamentoDTO.data());
        sessaoTreinamento.setHorarioTreinamento(horarioTreinamento);
        sessaoTreinamento = sessaoTreinamentoRepository.save(sessaoTreinamento);

        for (ExercicioTreinamentoDTO exercicioTreinamentoDTO : treinamentoDTO.exercicios()) {
            ItemSessaoTreinamento itemSessaoTreinamento = new ItemSessaoTreinamento();

            Exercicio exercicio = exercicioService.findById(exercicioTreinamentoDTO.id()).get();
            itemSessaoTreinamento.setExercicio(exercicio);
            itemSessaoTreinamento.setConcluida(exercicioTreinamentoDTO.concluido());
            itemSessaoTreinamento.setTempo(exercicioTreinamentoDTO.tempo());
            itemSessaoTreinamento.setSessao(sessaoTreinamento);

            itemSessaoTreinamentoService.save(itemSessaoTreinamento);
        }

    }
}
