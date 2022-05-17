package com.projetospringjpa.academia.repositories;

import com.projetospringjpa.academia.models.Avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
}
