package com.br.atletismo.repository;

import com.br.atletismo.model.Evento;
import com.br.atletismo.model.HorarioTreinamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioTreinamentoRepository extends JpaRepository<HorarioTreinamento, Long> {

    @Query("SELECT DISTINCT h FROM HorarioTreinamento h " +
            "JOIN FETCH h.evento ev " +
            "JOIN FETCH h.exercicios e " +
            "JOIN ev.clube c " +
            "JOIN c.usuarios u " +
            "WHERE u.email = :email")
    List<HorarioTreinamento> findHorariosWithExerciciosByUsuarioEmail(@Param("email") String email);

    @Query("SELECT DISTINCT h FROM HorarioTreinamento h " +
            "JOIN FETCH h.evento ev " +
            "JOIN FETCH h.exercicios e " +
            "JOIN ev.clube c " +
            "JOIN c.usuarios u " +
            "WHERE u.email = :email AND ev.id = :eventoId")
    List<HorarioTreinamento> findHorariosWithExerciciosByUsuarioEmailAndEvento(
            @Param("email") String email,
            @Param("eventoId") Long eventoId);

}
