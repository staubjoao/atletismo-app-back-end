package com.br.atletismo.service;

import com.br.atletismo.model.Clube;
import com.br.atletismo.repository.ClubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    public List<Clube> findAll() {
        return clubeRepository.findAll();
    }

    public Optional<Clube> findById(Long id) {
        return clubeRepository.findById(id);
    }

    public Clube save(Clube clube) {
        return clubeRepository.save(clube);
    }

    public void deleteById(Long id) {
        clubeRepository.deleteById(id);
    }

}
