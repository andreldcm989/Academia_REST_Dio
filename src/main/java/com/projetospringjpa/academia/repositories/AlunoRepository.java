package com.projetospringjpa.academia.repositories;

import com.projetospringjpa.academia.models.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
    boolean existsByCpf(String cpf);
}
