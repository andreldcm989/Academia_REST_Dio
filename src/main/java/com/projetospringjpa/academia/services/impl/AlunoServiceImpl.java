package com.projetospringjpa.academia.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.projetospringjpa.academia.models.Aluno;
import com.projetospringjpa.academia.models.Avaliacao;
import com.projetospringjpa.academia.models.dto.AlunoUpdateDto;
import com.projetospringjpa.academia.repositories.AlunoRepository;
import com.projetospringjpa.academia.services.AlunoService;
import com.projetospringjpa.academia.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Override
    public Aluno findById(Long id) {
        Optional<Aluno> aluno = repository.findById(id);
        return aluno.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public Page<Aluno> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public Aluno saveAluno(Aluno aluno){
        return repository.save(aluno);
    }

    @Override
    @Transactional
    public void deleteAluno(Aluno aluno) {
        repository.delete(aluno);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public Aluno update(Long id, AlunoUpdateDto alunoUpdateDto) {
        Aluno aluno = findById(id);
        aluno.setNome(alunoUpdateDto.getNome());
        aluno.setBairro(alunoUpdateDto.getBairro());
        aluno.setDtNasc(alunoUpdateDto.getDtNasc());
        saveAluno(aluno);
        return aluno;
    }

    @Override
    public List<Avaliacao> findAllAvaliacaoById(Long id) {
        Aluno aluno = findById(id);
        return aluno.getAvaliacoes();
    }

}
