package com.br.atletismo.repository;

import com.br.atletismo.model.EventoAtleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoAtletaRepository extends JpaRepository<EventoAtleta, Long> {
}
