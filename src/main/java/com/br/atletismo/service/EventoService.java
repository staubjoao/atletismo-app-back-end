package com.br.atletismo.service;

import com.br.atletismo.dto.ClubeDTO;
import com.br.atletismo.dto.EventoDTO;
import com.br.atletismo.dto.EventoRetornoDTO;
import com.br.atletismo.model.Atleta;
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

        return eventoRepository.findByClubeIn(clubeList);
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
        return eventoRepository.findByClube(clube);
    }

    public void vincularEventoAtleta(Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId).get();
        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Atleta atleta = new Atleta(usuario);

        List<Evento> eventoList = eventoRepository.findEventosByAtletaId(atleta.getId());
        if(!eventoList.contains(evento)) {
            eventoList.add(evento);
        }
        atleta.setEventos(eventoList);
        usuarioRepository.save(atleta);
    }

    public void desvincularEventoAtleta(Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Atleta atleta = new Atleta(usuario);

        List<Evento> eventoList = eventoRepository.findEventosByAtletaId(atleta.getId());

        if (eventoList.contains(evento)) {
            eventoList.remove(evento);
        }

        atleta.setEventos(eventoList);

        usuarioRepository.save(atleta);

        evento.getAtletas().remove(atleta);
        eventoRepository.save(evento);
    }


    public List<EventoRetornoDTO> getAllEventoAtleta() {
        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Atleta atleta = new Atleta(usuario);
        atleta.setEventos(eventoRepository.findEventosByAtletaId(atleta.getId()));

        List<Clube> clubes = atleta.getClubes();

        List<Evento> eventosDosClubes = eventoRepository.findByClubeIn(clubes);

        List<EventoRetornoDTO> eventoRetornoDTOs = new ArrayList<>();
        for (Evento evento : eventosDosClubes) {
            boolean vinculado = atleta.getEventos() != null && atleta.getEventos().contains(evento);
            EventoRetornoDTO dto = new EventoRetornoDTO(evento.getClube(), evento.getId(), evento.getNome(), evento.getTipo(), vinculado);
            eventoRetornoDTOs.add(dto);
        }

        return eventoRetornoDTOs;
    }

}
