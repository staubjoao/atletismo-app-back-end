package com.br.atletismo.service;

import com.br.atletismo.dto.ClubeDTO;
import com.br.atletismo.model.Clube;
import com.br.atletismo.model.Usuario;
import com.br.atletismo.repository.ClubeRepository;
import com.br.atletismo.repository.UsuarioRepository;
import com.br.atletismo.security.AuthenticatedUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Clube> findAll() {
        return clubeRepository.findAll();
    }

    public Optional<Clube> findById(Long id) {
        return clubeRepository.findById(id);
    }

    public Clube save(ClubeDTO clubeDTO) {
        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        Clube clube = new Clube();
        clube.setNome(clubeDTO.nome());
        String codigoClube = gerarCodigoUnico();
        clube.setCodigo(codigoClube);

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.getClubes().add(clube);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);
        clube.setUsuarios(usuarios);

        clubeRepository.save(clube);

        usuarioRepository.save(usuario);

        return clube;
    }

    public List<Clube> findClubesByAuthenticatedUser() {
        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return usuario.getClubes().stream().collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        clubeRepository.deleteById(id);
    }

    private String gerarCodigoUnico() {
        String codigo = gerarCodigo();

        Optional<Clube> clube = clubeRepository.findByCodigo(codigo);
        if (clube.isEmpty()) {
            return codigo;
        }

        return gerarCodigoUnico();
    }

    private String gerarCodigo() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder(5);
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

}
