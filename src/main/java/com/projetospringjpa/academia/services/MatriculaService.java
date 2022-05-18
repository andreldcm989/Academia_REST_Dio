package com.projetospringjpa.academia.services;

import com.projetospringjpa.academia.models.Aluno;
import com.projetospringjpa.academia.models.Matricula;
import com.projetospringjpa.academia.models.Turmas;
import com.projetospringjpa.academia.models.dto.MatriculaDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatriculaService {
    
    public Matricula saveMatricula(MatriculaDto matriculaDto);
    public Matricula update(Long id, MatriculaDto matriculaDto);
    public void delete(Matricula matricula);
    public Page<Matricula> findAll(Pageable pageable);
    public Matricula findById(Long id);
    public boolean conflitoDeHorario(Aluno aluno, Turmas turmas);
}
