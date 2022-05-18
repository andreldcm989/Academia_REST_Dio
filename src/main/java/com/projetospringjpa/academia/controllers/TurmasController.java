package com.projetospringjpa.academia.controllers;

import java.util.List;

import javax.validation.Valid;

import com.projetospringjpa.academia.models.Aluno;
import com.projetospringjpa.academia.models.Matricula;
import com.projetospringjpa.academia.models.Turmas;
import com.projetospringjpa.academia.models.dto.TurmasDto;
import com.projetospringjpa.academia.services.InstrutorService;
import com.projetospringjpa.academia.services.ModalidadeService;
import com.projetospringjpa.academia.services.TurmasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/turmas")
public class TurmasController {

    @Autowired
    private TurmasService service;

    @Autowired
    private ModalidadeService modalidadeService;

    @Autowired
    private InstrutorService instrutorService;

    @GetMapping
    public ResponseEntity<Page<Turmas>> findAll(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable pageable) {
        Page<Turmas> turmas = service.findAll(pageable);
        return ResponseEntity.ok().body(turmas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Turmas> findById(@PathVariable Long id){
        Turmas turmas = service.findById(id);
        return ResponseEntity.ok().body(turmas);
    }

    @PostMapping
    public ResponseEntity<Object> createTurmas(@RequestBody @Valid TurmasDto turmasDto){
        Long modalidade = modalidadeService.findById(turmasDto.getIdModalidade()).getId();
        if(modalidade != turmasDto.getIdModalidade()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modalidade não encontrada.");
        }
        if(service.conflitoDeHorario(instrutorService.findById(turmasDto.getIdInstrutor()), turmasDto)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito de horários: O Instrutor já possui turma neste horário!");
        }
        Turmas turmas = service.saveTurmas(turmasDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmas);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateTurmas(@PathVariable Long id, @RequestBody TurmasDto turmasDto){
        service.update(id, turmasDto);
        return ResponseEntity.ok().body("Cadastro atualizado!");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteTurmas(@PathVariable Long id){
        Turmas turmas = service.findById(id);
        service.delete(turmas);
        return ResponseEntity.ok().body("Turmas excluído com sucesso!");
    }

    @GetMapping(value = "/{id}/alunos")
    public ResponseEntity<List<Aluno>> findAlunosByTurmas(@PathVariable Long id){
        List<Aluno> alunos = service.findAlunosByTurma(id);
        return ResponseEntity.ok().body(alunos);
    }

    @GetMapping(value = "/{id}/matriculas")
    public ResponseEntity<List<Matricula>> findMatriculasByTurmas(@PathVariable Long id){
        List<Matricula> matriculas = service.findMatriculasByTurma(id);
        return ResponseEntity.ok().body(matriculas);
    }

}
