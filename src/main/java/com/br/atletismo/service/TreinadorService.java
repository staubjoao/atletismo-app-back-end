package com.br.atletismo.service;

import com.br.atletismo.model.Treinador;
import com.br.atletismo.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreinadorService {

    @Autowired
    private TreinadorRepository treinadorRepository;

    public List<Treinador> findAll() {
        return treinadorRepository.findAll();
    }

    public Optional<Treinador> findById(Long id) {
        return treinadorRepository.findById(id);
    }

    public Treinador save(Treinador treinador) {
        return treinadorRepository.save(treinador);
    }

    public void deleteById(Long id) {
        treinadorRepository.deleteById(id);
    }

}
