package com.projetospringjpa.academia.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.projetospringjpa.academia.models.Modalidade;
import com.projetospringjpa.academia.models.Turmas;
import com.projetospringjpa.academia.models.dto.ModalidadeDto;
import com.projetospringjpa.academia.repositories.ModalidadeRepository;
import com.projetospringjpa.academia.services.ModalidadeService;
import com.projetospringjpa.academia.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ModalidadeServiceImpl implements ModalidadeService {

    @Autowired
    private ModalidadeRepository repository;

    @Override
    public Page<Modalidade> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Modalidade findById(Long id) {
        Optional<Modalidade> Modalidade = repository.findById(id);
        return Modalidade.orElseThrow(() -> new ObjectNotFoundException("Modalidade não encontrada."));
    }

    @Override
    @Transactional
    public Modalidade saveModalidade(ModalidadeDto modalidadeDto) {
        Modalidade Modalidade = new Modalidade();
        Modalidade.setNome(modalidadeDto.getNome());
        Modalidade.setDtCriacao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        repository.save(Modalidade);
        return Modalidade;
    }

    @Override
    @Transactional
    public void delete(Modalidade Modalidade) {
        repository.delete(Modalidade);
    }

    @Override
    @Transactional
    public Modalidade update(Long id, ModalidadeDto ModalidadeDto) {
        Modalidade Modalidade = findById(id);
        Modalidade.setNome(ModalidadeDto.getNome());
        Modalidade.setDtCriacao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        repository.save(Modalidade);
        return Modalidade;
    }

    @Override
    public List<Turmas> findTurmasByModalidade(Long id) {
        List<Turmas> turmas = findById(id).getTurmas();
        return turmas;
    }
    
}
