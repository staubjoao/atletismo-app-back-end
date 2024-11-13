package com.br.atletismo.controller;

import com.br.atletismo.dto.UsuarioDTO;
import com.br.atletismo.model.Usuario;
import com.br.atletismo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins="*", maxAge = 3600)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/clube/{codigoClube}")
    public List<Usuario> findAllByClube(@PathVariable String codigoClube) {
        try {
            List<Usuario> usuarios = usuarioService.findByClube(codigoClube);
            return usuarios;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<?> atualizaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return ResponseEntity.ok(usuarioService.atualizaUsuario(usuarioDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
