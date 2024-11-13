package com.br.atletismo.controller;

import com.br.atletismo.dto.ClubeDTO;
import com.br.atletismo.dto.ClubeRetornoDTO;
import com.br.atletismo.dto.RespostaTextoDTO;
import com.br.atletismo.model.Clube;
import com.br.atletismo.service.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clube")
@CrossOrigin(origins="*", maxAge = 3600)
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @PostMapping
    public ResponseEntity<?> createClube(@RequestBody ClubeDTO clubeDTO) {
        try {
            Clube clubeResposta = clubeService.save(clubeDTO);
            return ResponseEntity.ok(clubeResposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllClube() {
        try {
            List<Clube> clubeResposta = clubeService.findClubesByAuthenticatedUser();
            return ResponseEntity.ok(clubeResposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/membros")
    public ResponseEntity<?> getAllClubeComMembros() {
        try {
            List<ClubeRetornoDTO> clubeResposta = clubeService.findClubesByAuthenticatedUserComMembros();
            return ResponseEntity.ok(clubeResposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idClube}")
    public ResponseEntity<?> updateClube(@RequestBody ClubeDTO clubeDTO, @PathVariable Long idClube) {
        try {
            Clube clubeResposta = clubeService.update(clubeDTO, idClube);
            return ResponseEntity.ok(clubeResposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idClube}")
    public ResponseEntity<?> deleteClube(@PathVariable Long idClube) {
        try {
            clubeService.deleteClub(idClube);
            return ResponseEntity.ok(new RespostaTextoDTO("Deletado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/entrar/{codigoClube}")
    public ResponseEntity<?> entrarClube(@PathVariable String codigoClube) {
        try {
            clubeService.entrarClube(codigoClube);
            return ResponseEntity.ok("{\"message\":\"Sessão salva com sucesso!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

