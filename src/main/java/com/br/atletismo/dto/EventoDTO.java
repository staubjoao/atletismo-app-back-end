package com.br.atletismo.dto;

import com.br.atletismo.model.enums.TipoEvento;

public record EventoDTO(String nome, TipoEvento tipo, Long idClube) {}
