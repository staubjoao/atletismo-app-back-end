package com.br.atletismo.repository;

import com.br.atletismo.model.Clube;
import com.br.atletismo.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    public List<Evento> findByClube(Clube clube);

}
