package com.projetospringjpa.academia.controllers;

import javax.validation.Valid;

import com.projetospringjpa.academia.models.Modalidade;
import com.projetospringjpa.academia.models.dto.ModalidadeDto;
import com.projetospringjpa.academia.services.ModalidadeService;

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
@RequestMapping(value = "/modalidades")
public class ModalidadeController {

    @Autowired
    private ModalidadeService service;

    @GetMapping
    public ResponseEntity<Page<Modalidade>> findAll(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable pageable) {
        Page<Modalidade> Modalidadees = service.findAll(pageable);
        return ResponseEntity.ok().body(Modalidadees);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Modalidade> findById(@PathVariable Long id){
        Modalidade Modalidade = service.findById(id);
        return ResponseEntity.ok().body(Modalidade);
    }

    @PostMapping
    public ResponseEntity<Object> createModalidade(@RequestBody @Valid ModalidadeDto ModalidadeDto){
        Modalidade Modalidade = service.saveModalidade(ModalidadeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Modalidade);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateModalidade(@PathVariable Long id, @RequestBody ModalidadeDto ModalidadeDto){
        service.update(id, ModalidadeDto);
        return ResponseEntity.ok().body("Cadastro atualizado!");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteModalidade(@PathVariable Long id){
        Modalidade Modalidade = service.findById(id);
        service.delete(Modalidade);
        return ResponseEntity.ok().body("Modalidade excluído com sucesso!");
    }

}
