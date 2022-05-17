package com.projetospringjpa.academia.services;

import com.projetospringjpa.academia.models.Avaliacao;
import com.projetospringjpa.academia.models.dto.AvaliacaoDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AvaliacaoService {
    
    public Page<Avaliacao> findAll(Pageable pageable);

    public Avaliacao createAvaliacao(AvaliacaoDto avaliacaoDto);
    
    public void deleteAvaliacao(Avaliacao avaliacao);

    public Avaliacao findById(Long id);
}
