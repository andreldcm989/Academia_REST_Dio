package com.projetospringjpa.academia.controllers;

import javax.validation.Valid;

import com.projetospringjpa.academia.models.Matricula;
import com.projetospringjpa.academia.models.dto.MatriculaDto;
import com.projetospringjpa.academia.services.AlunoService;
import com.projetospringjpa.academia.services.MatriculaService;
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
@RequestMapping(value = "/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService service;

    @Autowired 
    private TurmasService turmasService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<Page<Matricula>> findAll(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable pageable) {
        Page<Matricula> matricula = service.findAll(pageable);
        return ResponseEntity.ok().body(matricula);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Long id){
        Matricula matricula = service.findById(id);
        return ResponseEntity.ok().body(matricula);
    }

    @PostMapping
    public ResponseEntity<Object> createMatricula(@RequestBody @Valid MatriculaDto matriculaDto){
        Long turma = turmasService.findById(matriculaDto.getIdTurma()).getIdTurma();
        boolean alunoNaTurma = turmasService.findAlunosByTurma(matriculaDto.getIdTurma()).contains(alunoService.findById(matriculaDto.getIdAluno()));
        if(turma != matriculaDto.getIdTurma()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada.");
        }
        if(alunoNaTurma){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O aluno já está matriculado nesta turma!");
        }
        if(service.conflitoDeHorario(alunoService.findById(matriculaDto.getIdAluno()), turmasService.findById(matriculaDto.getIdTurma()))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito de horários: O aluno já possui aulas neste horário!");
        }
        Matricula matricula = service.saveMatricula(matriculaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateMatricula(@PathVariable Long id, @RequestBody MatriculaDto MatriculaDto){
        service.update(id, MatriculaDto);
        return ResponseEntity.ok().body("Cadastro atualizado!");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteMatricula(@PathVariable Long id){
        Matricula Matricula = service.findById(id);
        service.delete(Matricula);
        return ResponseEntity.ok().body("Matricula excluído com sucesso!");
    }

}
