package com.br.atletismo.service;

import com.br.atletismo.model.Atleta;
import com.br.atletismo.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtletaService {

    @Autowired
    private AtletaRepository atletaRepository;

    public List<Atleta> findAll() {
        return atletaRepository.findAll();
    }

    public Optional<Atleta> findById(Long id) {
        return atletaRepository.findById(id);
    }

    public Atleta save(Atleta atleta) {
        return atletaRepository.save(atleta);
    }

    public void deleteById(Long id) {
        atletaRepository.deleteById(id);
    }

}
