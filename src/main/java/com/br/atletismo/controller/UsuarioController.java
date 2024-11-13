package com.br.atletismo.controller;

import com.br.atletismo.dto.AlterarSenhaDTO;
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

    @PutMapping("/alterar-senha")
    public ResponseEntity<?> alterarSenha(@RequestBody AlterarSenhaDTO alterarSenhaDTO) {
        try {
            usuarioService.atualizarSenha(alterarSenhaDTO.senhaAtual(), alterarSenhaDTO.novaSenha());
            return ResponseEntity.ok("{\"message\":\"Senha alterada com sucesso!\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao alterar senha");
        }
    }

    @GetMapping("/obter-usuario-ativo")
    public ResponseEntity<?> obterUsuarioAtivo() {
        return ResponseEntity.ok(usuarioService.usuarioAutenticado());
    }

    @PutMapping("/atualizar-usuario")
    public ResponseEntity<?> atualizaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return ResponseEntity.ok(usuarioService.atualizaUsuario(usuarioDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
