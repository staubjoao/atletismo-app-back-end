package com.br.atletismo.service;

import com.br.atletismo.dto.UsuarioDTO;
import com.br.atletismo.model.Atleta;
import com.br.atletismo.model.Clube;
import com.br.atletismo.model.Treinador;
import com.br.atletismo.model.Usuario;
import com.br.atletismo.repository.ClubeRepository;
import com.br.atletismo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.br.atletismo.model.enums.FuncaoUsuario.ATLETA;
import static com.br.atletismo.model.enums.FuncaoUsuario.TREINADOR;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClubeRepository clubeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(UsuarioDTO usuarioDTO) {
        Usuario usuario;

        if (usuarioDTO.funcao() == ATLETA) {
            usuario = new Atleta();
        } else if (usuarioDTO.funcao() == TREINADOR) {
            usuario = new Treinador();
        } else {
            throw new IllegalArgumentException("Função de usuário desconhecida");
        }

        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));
        usuario.setFuncao(usuarioDTO.funcao());

        return usuarioRepository.save(usuario);
    }

    public Usuario adicionarClubeAoUsuario(Long usuarioId, String codigoClube) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Clube clube = clubeRepository.findByCodigo(codigoClube)
                .orElseThrow(() -> new IllegalArgumentException("Clube não encontrado"));

        usuario.getClubes().add(clube);

        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

}
