package com.br.atletismo.repository;

import com.br.atletismo.model.ItemSessaoTreinamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSessaoTreinamentoRepository extends JpaRepository<ItemSessaoTreinamento, Long> {
}
