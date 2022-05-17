package com.projetospringjpa.academia.controllers;

import java.util.List;

import javax.validation.Valid;

import com.projetospringjpa.academia.models.Aluno;
import com.projetospringjpa.academia.models.Avaliacao;
import com.projetospringjpa.academia.models.dto.AlunoDto;
import com.projetospringjpa.academia.models.dto.AlunoUpdateDto;
import com.projetospringjpa.academia.services.AlunoService;

import org.springframework.beans.BeanUtils;
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
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public ResponseEntity<Page<Aluno>> findAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        Aluno aluno = service.findById(id);
        return ResponseEntity.ok().body(aluno);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateAluno(@PathVariable Long id,
            @RequestBody @Valid AlunoUpdateDto alunoUpdateDto) {
        service.update(id, alunoUpdateDto);
        return ResponseEntity.ok().body("Cadastro atualizado!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAluno(@PathVariable Long id) {
        Aluno aluno = service.findById(id);
        service.deleteAluno(aluno);
        return ResponseEntity.ok().body("Operação realizada com sucesso!");
    }

    @PostMapping
    public ResponseEntity<Object> createAluno(@RequestBody @Valid AlunoDto alunoDto){
        if(service.existsByCpf(alunoDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF existente na base de dados.");
        }
        var aluno = new Aluno();
        BeanUtils.copyProperties(alunoDto, aluno);
        service.saveAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @GetMapping(value = "/{id}/avaliacoes")
    public ResponseEntity<List<Avaliacao>> findAvaliacaoByAluno(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findAllAvaliacaoById(id));
    }

}
