package com.br.atletismo.controller;

import com.br.atletismo.dto.ClubeDTO;
import com.br.atletismo.dto.EventoDTO;
import com.br.atletismo.dto.EventoRetornoDTO;
import com.br.atletismo.model.Clube;
import com.br.atletismo.model.Evento;
import com.br.atletismo.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evento")
@CrossOrigin(origins="*", maxAge = 3600)
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<?> createEvento(@RequestBody EventoDTO eventoDTO) {
        try {
            Evento eventoResposta = eventoService.save(eventoDTO);
            return ResponseEntity.ok(eventoResposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEvento() {
        try {
            List<Evento> clubeResposta = eventoService.findEventosByClubeByAuthenticatedUser();
            return ResponseEntity.ok(clubeResposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idEvento}")
    public ResponseEntity<?> updateEvento(@RequestBody EventoDTO eventoDTO, @PathVariable Long idEvento) {
        try {
            Evento eventoResposta = eventoService.update(eventoDTO, idEvento);
            return ResponseEntity.ok(eventoResposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/clube/{clubeId}")
    public ResponseEntity<?> getEventoByClube(@PathVariable Long clubeId) {
        List<Evento> eventos = eventoService.findAllEventosByClube(clubeId);
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/vincular/{idEvento}")
    public ResponseEntity<?> vincularEventoAtleta(@PathVariable Long idEvento) {
        try {
            eventoService.vincularEventoAtleta(idEvento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/desvincular/{idEvento}")
    public ResponseEntity<?> desvincularEventoAtleta(@PathVariable Long idEvento) {
        try {
            eventoService.desvincularEventoAtleta(idEvento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/atleta")
    public ResponseEntity<?> getAllEventoAtleta() {
        try {
            List<EventoRetornoDTO> eventoRetornoDTOList = eventoService.getAllEventoAtleta();
            return ResponseEntity.ok().body(eventoRetornoDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
