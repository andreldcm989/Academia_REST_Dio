package com.projetospringjpa.academia.services;

import java.util.List;

import com.projetospringjpa.academia.models.Aluno;
import com.projetospringjpa.academia.models.Instrutor;
import com.projetospringjpa.academia.models.Matricula;
import com.projetospringjpa.academia.models.Turmas;
import com.projetospringjpa.academia.models.dto.TurmasDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TurmasService {
    
    public Page<Turmas> findAll(Pageable pageable);
    public Turmas findById(Long id);
    public Turmas saveTurmas(TurmasDto TurmasDto);
    public void delete(Turmas Turmas);
    public Turmas update(Long id, TurmasDto TurmasDto);
    public List<Aluno> findAlunosByTurma(Long id);
    public List<Matricula> findMatriculasByTurma(Long id);
    public boolean conflitoDeHorario(Instrutor instrutor, TurmasDto turmasDto);
}
