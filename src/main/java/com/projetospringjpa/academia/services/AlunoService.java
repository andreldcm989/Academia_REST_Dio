package com.projetospringjpa.academia.services;

import java.util.List;

import com.projetospringjpa.academia.models.Aluno;
import com.projetospringjpa.academia.models.Avaliacao;
import com.projetospringjpa.academia.models.dto.AlunoUpdateDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface AlunoService {

    public Page<Aluno> findAll(Pageable pageable);

    public Aluno findById(Long id);

    public Aluno saveAluno(Aluno aluno);

    public void deleteAluno(Aluno aluno);

    public boolean existsByCpf(String cpf);

    public Aluno update(Long id, AlunoUpdateDto alunoUpdateDto);

    public List<Avaliacao> findAllAvaliacaoById(Long id);

}
