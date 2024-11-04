package com.br.atletismo.controller;

import com.br.atletismo.dto.LoginDTO;
import com.br.atletismo.dto.RecoveryJwtTokenDTO;
import com.br.atletismo.dto.UsuarioDTO;
import com.br.atletismo.model.Usuario;
import com.br.atletismo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*", maxAge = 3600)
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuarioResposta = usuarioService.save(usuarioDTO);
            return ResponseEntity.ok(usuarioResposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/clube/{codigoClube}")
    public ResponseEntity<Usuario> createUsuario(@RequestBody UsuarioDTO usuarioDTO, @PathVariable String codigoClube) {
        try {
            Usuario usuarioResposta = usuarioService.saveComClube(usuarioDTO, codigoClube);
            return ResponseEntity.ok(usuarioResposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            RecoveryJwtTokenDTO recoveryJwtTokenDTO = usuarioService.authenticateUser(loginDTO);
            return ResponseEntity.ok(recoveryJwtTokenDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

