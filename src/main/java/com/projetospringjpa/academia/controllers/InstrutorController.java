package com.projetospringjpa.academia.controllers;

import javax.validation.Valid;

import com.projetospringjpa.academia.models.Instrutor;
import com.projetospringjpa.academia.models.dto.InstrutorDto;
import com.projetospringjpa.academia.services.InstrutorService;

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
@RequestMapping(value = "/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorService service;

    @GetMapping
    public ResponseEntity<Page<Instrutor>> findAll(@PageableDefault(page = 0, size = 10, direction = Direction.ASC) Pageable pageable) {
        Page<Instrutor> instrutores = service.findAll(pageable);
        return ResponseEntity.ok().body(instrutores);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Instrutor> findById(@PathVariable Long id){
        Instrutor instrutor = service.findById(id);
        return ResponseEntity.ok().body(instrutor);
    }

    @PostMapping
    public ResponseEntity<Object> createInstrutor(@RequestBody @Valid InstrutorDto instrutorDto){
        if(service.existsByCpf(instrutorDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF existente na base de dados.");
        }
        Instrutor instrutor = service.saveInstrutor(instrutorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(instrutor);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateInstrutor(@PathVariable Long id, @RequestBody InstrutorDto instrutorDto){
        service.update(id, instrutorDto);
        return ResponseEntity.ok().body("Cadastro atualizado!");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteInstrutor(@PathVariable Long id){
        Instrutor instrutor = service.findById(id);
        service.delete(instrutor);
        return ResponseEntity.ok().body("Instrutor excluído com sucesso!");
    }

}
