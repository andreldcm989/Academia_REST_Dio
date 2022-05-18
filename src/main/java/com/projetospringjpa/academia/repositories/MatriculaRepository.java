package com.projetospringjpa.academia.repositories;

import com.projetospringjpa.academia.models.Matricula;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    
}
