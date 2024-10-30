package com.br.atletismo.service;

import com.br.atletismo.config.SecurityConfiguration;
import com.br.atletismo.dto.LoginDTO;
import com.br.atletismo.dto.RecoveryJwtTokenDTO;
import com.br.atletismo.dto.UsuarioDTO;
import com.br.atletismo.model.Atleta;
import com.br.atletismo.model.Clube;
import com.br.atletismo.model.Treinador;
import com.br.atletismo.model.Usuario;
import com.br.atletismo.repository.ClubeRepository;
import com.br.atletismo.repository.UsuarioRepository;
import com.br.atletismo.security.JwtTokenService;
import com.br.atletismo.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public RecoveryJwtTokenDTO authenticateUser(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtTokenService.generateToken(userDetails);
        String funcao = userDetails.getUsuario().getFuncao().toString();
        String usuarioId = userDetails.getUsuario().getId().toString();

        return new RecoveryJwtTokenDTO(token, funcao, usuarioId);
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
        usuario.setSenha(securityConfiguration.passwordEncoder().encode(usuarioDTO.senha()));
        usuario.setFuncao(usuarioDTO.funcao());

        return usuarioRepository.save(usuario);
    }

    public Usuario saveComClube(UsuarioDTO usuarioDTO, String codigoClube) {
        Usuario usuario;

        Clube clube = clubeRepository.findByCodigo(codigoClube).get();

        List<Clube> clubes = new ArrayList<>();
        clubes.add(clube);
        if (usuarioDTO.funcao() == ATLETA) {
            usuario = new Atleta();
        } else if (usuarioDTO.funcao() == TREINADOR) {
            usuario = new Treinador();
        } else {
            throw new IllegalArgumentException("Função de usuário desconhecida");
        }

        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(securityConfiguration.passwordEncoder().encode(usuarioDTO.senha()));
        usuario.setFuncao(usuarioDTO.funcao());
        usuario.setClubes(clubes);
        Usuario usuario1 = usuarioRepository.save(usuario);
        List<Usuario> usuarios = clube.getUsuarios();
        usuarios.add(usuario1);
        clube.setUsuarios(usuarios);

        return usuario1;

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

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

}
