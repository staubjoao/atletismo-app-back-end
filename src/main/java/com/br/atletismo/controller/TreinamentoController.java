package com.br.atletismo.controller;

import com.br.atletismo.dto.treinamento.TreinamentoDTO;
import com.br.atletismo.service.TreinamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessao-treino")
@CrossOrigin(origins="*", maxAge = 3600)
public class TreinamentoController {

    @Autowired
    private TreinamentoService treinamentoService;

    @PostMapping
    public ResponseEntity<?> salvarSessaoTreinamento(@RequestBody TreinamentoDTO treinamentoDTO) {
        try {
            treinamentoService.salvarTreinamento(treinamentoDTO);
            return ResponseEntity.ok("{\"message\":\"Sessão salva com sucesso!\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
