package com.projetospringjpa.academia.repositories;

import com.projetospringjpa.academia.models.Instrutor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long>  {
    boolean existsByCpf(String cpf);
}
