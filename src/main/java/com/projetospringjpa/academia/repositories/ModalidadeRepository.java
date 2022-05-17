package com.projetospringjpa.academia.repositories;

import com.projetospringjpa.academia.models.Modalidade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalidadeRepository extends JpaRepository<Modalidade, Long>  {
    boolean existsByNome(String nome);
}
