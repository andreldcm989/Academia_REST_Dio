package com.projetospringjpa.academia.services;

import java.util.List;

import com.projetospringjpa.academia.models.Instrutor;
import com.projetospringjpa.academia.models.Turmas;
import com.projetospringjpa.academia.models.dto.InstrutorDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InstrutorService {
    
    public Page<Instrutor> findAll(Pageable pageable);
    public Instrutor findById(Long id);
    public Instrutor saveInstrutor(InstrutorDto instrutorDto);
    public void delete(Instrutor instrutor);
    public Instrutor update(Long id, InstrutorDto instrutorDto);
    public boolean existsByCpf(String cpf);
    public List<Turmas> findTurmasByInstrutor(Long id);
}
