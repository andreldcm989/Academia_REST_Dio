package com.projetospringjpa.academia.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import com.projetospringjpa.academia.models.Aluno;
import com.projetospringjpa.academia.models.Avaliacao;
import com.projetospringjpa.academia.models.dto.AvaliacaoDto;
import com.projetospringjpa.academia.repositories.AlunoRepository;
import com.projetospringjpa.academia.repositories.AvaliacaoRepository;
import com.projetospringjpa.academia.services.AvaliacaoService;
import com.projetospringjpa.academia.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Page<Avaliacao> findAll(Pageable pageable) {
        return avaliacaoRepository.findAll(pageable);
    }

    @Override
    public Avaliacao createAvaliacao(AvaliacaoDto avaliacaoDto) {
        Avaliacao avaliacao = new Avaliacao();
        Aluno aluno = alunoRepository.findById(avaliacaoDto.getAlunoId()).get();
        BeanUtils.copyProperties(avaliacaoDto, avaliacao);
        avaliacao.setDtAvaliacao(LocalDateTime.now());
        avaliacao.setAluno(aluno);
        avaliacaoRepository.save(avaliacao);
        return avaliacao;
    }

    @Override
    public void deleteAvaliacao(Avaliacao avaliacao) {
        avaliacaoRepository.delete(avaliacao);
    }

    @Override
    public Avaliacao findById(Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        return avaliacao.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
    
}
