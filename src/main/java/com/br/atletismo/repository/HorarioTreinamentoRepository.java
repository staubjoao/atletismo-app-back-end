package com.br.atletismo.repository;

import com.br.atletismo.model.HorarioTreinamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioTreinamentoRepository extends JpaRepository<HorarioTreinamento, Long> {
}
