package com.br.atletismo.dto;

import com.br.atletismo.model.enums.FuncaoUsuario;

public record UsuarioDTO(String nome, String email, String senha, FuncaoUsuario funcao) {}
