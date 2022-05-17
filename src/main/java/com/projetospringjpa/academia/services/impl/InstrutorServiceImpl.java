package com.projetospringjpa.academia.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import com.projetospringjpa.academia.models.Instrutor;
import com.projetospringjpa.academia.models.dto.InstrutorDto;
import com.projetospringjpa.academia.repositories.InstrutorRepository;
import com.projetospringjpa.academia.services.InstrutorService;
import com.projetospringjpa.academia.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstrutorServiceImpl implements InstrutorService {

    @Autowired
    private InstrutorRepository repository;

    @Override
    public Page<Instrutor> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Instrutor findById(Long id) {
        Optional<Instrutor> instrutor = repository.findById(id);
        return instrutor.orElseThrow(() -> new ObjectNotFoundException("Instrutor não encontrado."));
    }

    @Override
    @Transactional
    public Instrutor saveInstrutor(InstrutorDto instrutorDto) {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome(instrutorDto.getNome());
        instrutor.setCpf(instrutorDto.getCpf());
        instrutor.setFormacao(instrutorDto.getFormacao());
        instrutor.setDtCadastro(LocalDateTime.now());
        repository.save(instrutor);
        return instrutor;
    }

    @Override
    @Transactional
    public void delete(Instrutor instrutor) {
        repository.delete(instrutor);
    }

    @Override
    @Transactional
    public Instrutor update(Long id, InstrutorDto instrutorDto) {
        Instrutor instrutor = findById(id);
        instrutor.setNome(instrutorDto.getNome());
        instrutor.setCpf(instrutorDto.getCpf());
        instrutor.setFormacao(instrutorDto.getFormacao());
        repository.save(instrutor);
        return instrutor;
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }
    
}
