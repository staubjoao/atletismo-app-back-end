package com.br.atletismo.service;

import com.br.atletismo.dto.ClubeDTO;
import com.br.atletismo.dto.ClubeRetornoDTO;
import com.br.atletismo.model.Clube;
import com.br.atletismo.model.Usuario;
import com.br.atletismo.repository.ClubeRepository;
import com.br.atletismo.repository.UsuarioRepository;
import com.br.atletismo.security.AuthenticatedUserUtil;
import jakarta.transaction.Transactional;
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

    public Clube update(ClubeDTO clubeDTO, Long idClube) {
        Clube clube = clubeRepository.findById(idClube).get();
        clube.setNome(clubeDTO.nome());
        return clubeRepository.save(clube);
    }

    public List<Clube> findClubesByAuthenticatedUser() {
        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return usuario.getClubes().stream().collect(Collectors.toList());
    }

    public List<ClubeRetornoDTO> findClubesByAuthenticatedUserComMembros() {
        String email = AuthenticatedUserUtil.getAuthenticatedUsername();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));



        List<Clube> clubeList = usuario.getClubes().stream().collect(Collectors.toList());

        List<ClubeRetornoDTO> retornoDTOList = new ArrayList<>();

        for (Clube clube : clubeList) {
            ClubeRetornoDTO clubeRetornoDTO = new ClubeRetornoDTO(clube.getNome(), clube.getCodigo(), clube.getUsuarios().size());
            retornoDTOList.add(clubeRetornoDTO);
        }
        return retornoDTOList;
    }

    @Transactional
    public void deleteClub(Long clubeId) {
        Clube clube = clubeRepository.findById(clubeId)
                .orElseThrow(() -> new RuntimeException("Clube não encontrado"));

        for (Usuario usuario : clube.getUsuarios()) {
            usuario.getClubes().remove(clube);
            usuarioRepository.save(usuario);
        }

        clubeRepository.delete(clube);
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
