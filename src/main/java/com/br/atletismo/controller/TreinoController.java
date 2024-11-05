package com.br.atletismo.controller;

import com.br.atletismo.dto.treino.HorarioTreinamentoDTO;
import com.br.atletismo.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treino")
@CrossOrigin(origins="*", maxAge = 3600)
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @PostMapping
    public ResponseEntity<?> uploadTrainingSchedule(@RequestBody List<HorarioTreinamentoDTO> horarioTreinamentoDTOList) {
        try {
            treinoService.salvarTreino(horarioTreinamentoDTOList);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
