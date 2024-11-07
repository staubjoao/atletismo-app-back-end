package com.br.atletismo.dto;

import com.br.atletismo.model.Clube;
import com.br.atletismo.model.enums.TipoEvento;

public record EventoRetornoDTO (Clube clube, Long id, String nome, TipoEvento tipo, boolean vinculado){
}
