package com.br.atletismo.repository;

import com.br.atletismo.model.SessaoTreinamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoTreinamentoRepository extends JpaRepository<SessaoTreinamento, Long> {
}
