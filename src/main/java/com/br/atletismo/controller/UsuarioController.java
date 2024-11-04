package com.br.atletismo.controller;

import com.br.atletismo.model.Usuario;
import com.br.atletismo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
