package com.br.atletismo.service;

import com.br.atletismo.model.EventoAtleta;
import com.br.atletismo.repository.EventoAtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoAtletaService {

    @Autowired
    private EventoAtletaRepository eventoAtletaRepository;

    public List<EventoAtleta> findAll() {
        return eventoAtletaRepository.findAll();
    }

    public Optional<EventoAtleta> findById(Long id) {
        return eventoAtletaRepository.findById(id);
    }

    public EventoAtleta save(EventoAtleta eventoAtleta) {
        return eventoAtletaRepository.save(eventoAtleta);
    }

    public void deleteById(Long id) {
        eventoAtletaRepository.deleteById(id);
    }
}
