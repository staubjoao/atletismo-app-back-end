package com.br.atletismo.service;

import com.br.atletismo.model.SessaoTreinamento;
import com.br.atletismo.repository.SessaoTreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessaoTreinamentoService {

    @Autowired
    private SessaoTreinamentoRepository sessaoTreinamentoRepository;

    public List<SessaoTreinamento> findAll() {
        return sessaoTreinamentoRepository.findAll();
    }

    public Optional<SessaoTreinamento> findById(Long id) {
        return sessaoTreinamentoRepository.findById(id);
    }

    public SessaoTreinamento save(SessaoTreinamento sessao) {
        return sessaoTreinamentoRepository.save(sessao);
    }

    public void deleteById(Long id) {
        sessaoTreinamentoRepository.deleteById(id);
    }
}
