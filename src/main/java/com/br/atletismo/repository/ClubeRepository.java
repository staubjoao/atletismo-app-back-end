package com.br.atletismo.repository;

import com.br.atletismo.model.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {

    Optional<Clube> findByCodigo(String codigo);

}
