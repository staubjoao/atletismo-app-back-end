package com.br.atletismo.service;

import com.br.atletismo.dto.ClubeDTO;
import com.br.atletismo.dto.EventoDTO;
import com.br.atletismo.model.Clube;
import com.br.atletismo.model.Evento;
import com.br.atletismo.model.Usuario;
import com.br.atletismo.repository.ClubeRepository;
import com.br.atletismo.repository.EventoRepository;
import com.br.atletismo.repository.UsuarioRepository;
import com.br.atletismo.security.AuthenticatedUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ClubeRepository clubeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento save(EventoDTO eventoDTO) {
        Evento evento = new Evento();
        evento.setNome(eventoDTO.nome());
        evento.setTipo(eventoDTO.tipo());

        Clube clube = clubeRepository.findById(eventoDTO.idClube())
                .orElseThrow(() -> new RuntimeException("Clube não encontrado"));
        evento.setClube(clube);
        return eventoRepository.save(evento);
    }

    public List<Evento> findEventosByClubeByAuthenticatedUser() {
        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Clube> clubeList = usuario.getClubes();

        List<Evento> eventos = new ArrayList<>();
        for (Clube clube : clubeList) {
            eventos.addAll(clube.getEventos());
        }

        return eventos;
    }

    public Evento update(EventoDTO eventoDTO, Long idEvento) {
        Evento evento = eventoRepository.findById(idEvento).get();
        evento.setNome(eventoDTO.nome());
        return eventoRepository.save(evento);
    }


    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> findAllEventosByClube(Long clubeId) {
        Clube clube = clubeRepository.findById(clubeId).get();
        return clube.getEventos();
    }

}
