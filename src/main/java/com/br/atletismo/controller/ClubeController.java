package com.br.atletismo.controller;

import com.br.atletismo.dto.ClubeDTO;
import com.br.atletismo.model.Clube;
import com.br.atletismo.service.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clube")
@CrossOrigin(origins="*", maxAge = 3600)
public class ClubeController {

    @Autowired
    private ClubeService clubeService;

    @PostMapping
    public ResponseEntity<Clube> createClube(@RequestBody ClubeDTO clubeDTO) {
        try {
            Clube clubeResposta = clubeService.save(clubeDTO);
            return ResponseEntity.ok(clubeResposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

