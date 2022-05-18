package com.projetospringjpa.academia.services.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.transaction.Transactional;

import com.projetospringjpa.academia.models.Aluno;
import com.projetospringjpa.academia.models.Matricula;
import com.projetospringjpa.academia.models.Turmas;
import com.projetospringjpa.academia.models.dto.MatriculaDto;
import com.projetospringjpa.academia.repositories.AlunoRepository;
import com.projetospringjpa.academia.repositories.MatriculaRepository;
import com.projetospringjpa.academia.repositories.TurmasRepository;
import com.projetospringjpa.academia.services.MatriculaService;
import com.projetospringjpa.academia.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmasRepository turmasRepository;

    @Override
    public Page<Matricula> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Matricula findById(Long id) {
        Optional<Matricula> matricula = repository.findById(id);
        return matricula.orElseThrow(() -> new ObjectNotFoundException("Matricula não encontrada."));
    }

    @Override
    @Transactional
    public Matricula saveMatricula(MatriculaDto matriculaDto) {
        Matricula matricula = new Matricula();
        matricula.setDtMatricula(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        matricula.setAluno(alunoRepository.findById(matriculaDto.getIdAluno()).get());
        matricula.setTurma(turmasRepository.findById(matriculaDto.getIdTurma()).get());
        repository.save(matricula);
        return matricula;
    }

    @Override
    @Transactional
    public void delete(Matricula matricula) {
        repository.delete(matricula);
    }

    @Override
    @Transactional
    public Matricula update(Long id, MatriculaDto matriculaDto) {
        Matricula matricula = findById(id);
        matricula.setAluno(alunoRepository.findById(matriculaDto.getIdAluno()).get());
        matricula.setTurma(turmasRepository.findById(matriculaDto.getIdTurma()).get());
        repository.save(matricula);
        return matricula;
    }

    @Override
    public boolean conflitoDeHorario(Aluno aluno, Turmas turmas) {
        for (Turmas t : aluno.getTurmas()) {
            LocalTime inicio = t.getHorario();
            LocalTime fim = t.hrTerminoAula();
            if(turmas.getHorario().isAfter(inicio) && turmas.getHorario().isBefore(fim) ||
                turmas.hrTerminoAula().isAfter(inicio) && turmas.hrTerminoAula().isBefore(fim) ||
                inicio.isAfter(turmas.getHorario()) && inicio.isBefore(turmas.hrTerminoAula()) ||
                fim.isAfter(turmas.getHorario()) && fim.isBefore(turmas.hrTerminoAula()) ||
                turmas.getHorario() == inicio){
                return true;
            }
        }
        return false;
    }

}
