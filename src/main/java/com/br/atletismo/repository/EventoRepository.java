package com.br.atletismo.repository;

import com.br.atletismo.model.Clube;
import com.br.atletismo.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByClube(Clube clube);

    List<Evento> findByClubeIn(List<Clube> clubes);

    @Query("SELECT e FROM Evento e JOIN e.atletas a WHERE a.id = :atletaId")
    List<Evento> findEventosByAtletaId(@Param("atletaId") Long atletaId);
}
