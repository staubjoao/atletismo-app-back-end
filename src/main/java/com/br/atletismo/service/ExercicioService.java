package com.br.atletismo.service;

import com.br.atletismo.model.Exercicio;
import com.br.atletismo.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<Exercicio> findAll() {
        return exercicioRepository.findAll();
    }

    public Optional<Exercicio> findById(Long id) {
        return exercicioRepository.findById(id);
    }

    public Exercicio save(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    public void deleteById(Long id) {
        exercicioRepository.deleteById(id);
    }

}
