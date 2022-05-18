package com.projetospringjpa.academia.services.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.projetospringjpa.academia.models.Aluno;
import com.projetospringjpa.academia.models.Instrutor;
import com.projetospringjpa.academia.models.Matricula;
import com.projetospringjpa.academia.models.Turmas;
import com.projetospringjpa.academia.models.dto.TurmasDto;
import com.projetospringjpa.academia.repositories.TurmasRepository;
import com.projetospringjpa.academia.services.InstrutorService;
import com.projetospringjpa.academia.services.ModalidadeService;
import com.projetospringjpa.academia.services.TurmasService;
import com.projetospringjpa.academia.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TurmasServiceImpl implements TurmasService {

    @Autowired
    private TurmasRepository repository;

    @Autowired
    private ModalidadeService modalidadeService;

    @Autowired
    private InstrutorService instrutorService;

    @Override
    public Page<Turmas> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Turmas findById(Long id) {
        Optional<Turmas> turmas = repository.findById(id);
        return turmas.orElseThrow(() -> new ObjectNotFoundException("Turma não encontrada."));
    }

    @Override
    @Transactional
    public Turmas saveTurmas(TurmasDto turmasDto) {
        Turmas turmas = new Turmas();
        turmas.setHorario(turmasDto.getHorario());
        turmas.setDuracao(turmasDto.getDuracao());
        turmas.setModalidade(modalidadeService.findById(turmasDto.getIdModalidade()));
        turmas.setInstrutor(instrutorService.findById(turmasDto.getIdInstrutor()));
        repository.save(turmas);
        return turmas;
    }

    @Override
    @Transactional
    public void delete(Turmas turmas) {
        repository.delete(turmas);
    }

    @Override
    @Transactional
    public Turmas update(Long id, TurmasDto turmasDto) {
        Turmas turmas = findById(id);
        turmas.setHorario(turmasDto.getHorario());
        turmas.setDuracao(turmasDto.getDuracao());
        turmas.setModalidade(modalidadeService.findById(turmasDto.getIdModalidade()));
        turmas.setInstrutor(instrutorService.findById(turmasDto.getIdInstrutor()));
        repository.save(turmas);
        return turmas;
    }

    @Override
    public List<Aluno> findAlunosByTurma(Long id) {
        List<Aluno> alunos = findById(id).getAlunos();
        return alunos;
    }

    @Override
    public List<Matricula> findMatriculasByTurma(Long id) {
        List<Matricula> matriculas = findById(id).getMatriculas();
        return matriculas;
    }

    @Override
    public boolean conflitoDeHorario(Instrutor instrutor, TurmasDto turmas) {
        for (Turmas t : instrutor.getTurmas()) {
            LocalTime inicio = t.getHorario();
            LocalTime fim = t.hrTerminoAula();
            if (turmas.getHorario().isAfter(inicio) && turmas.getHorario().isBefore(fim) ||
                    turmas.hrTerminoAula().isAfter(inicio) && turmas.hrTerminoAula().isBefore(fim) ||
                    inicio.isAfter(turmas.getHorario()) && inicio.isBefore(turmas.hrTerminoAula()) ||
                    fim.isAfter(turmas.getHorario()) && fim.isBefore(turmas.hrTerminoAula()) ||
                    turmas.getHorario() == inicio) {
                return true;
            }
        }
        return false;
    }

}
