package com.br.atletismo.service;

import com.br.atletismo.model.HorarioTreinamento;
import com.br.atletismo.repository.HorarioTreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioTreinamentoService {

    @Autowired
    private HorarioTreinamentoRepository horarioTreinamentoRepository;

    public List<HorarioTreinamento> findAll() {
        return horarioTreinamentoRepository.findAll();
    }

    public Optional<HorarioTreinamento> findById(Long id) {
        return horarioTreinamentoRepository.findById(id);
    }

    public HorarioTreinamento save(HorarioTreinamento horario) {
        return horarioTreinamentoRepository.save(horario);
    }

    public void deleteById(Long id) {
        horarioTreinamentoRepository.deleteById(id);
    }
}
