package com.projetospringjpa.academia.controllers;

import javax.validation.Valid;

import com.projetospringjpa.academia.models.Avaliacao;
import com.projetospringjpa.academia.models.dto.AvaliacaoDto;
import com.projetospringjpa.academia.services.AvaliacaoService;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @GetMapping
    public ResponseEntity<Page<Avaliacao>> findAll(@PageableDefault(page = 0,sort = "id", size = 10, direction = Direction.ASC) Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Object> createAvaliacao(@RequestBody @Valid AvaliacaoDto avaliacaoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAvaliacao(avaliacaoDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAvaliacao(@PathVariable Long id){
        Avaliacao avaliacao = service.findById(id);
        service.deleteAvaliacao(avaliacao);
        return ResponseEntity.ok().body("Avaliação excluída com sucesso!");
    }
    
}
