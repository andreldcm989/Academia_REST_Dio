package com.projetospringjpa.academia.services;

import com.projetospringjpa.academia.models.Modalidade;
import com.projetospringjpa.academia.models.dto.ModalidadeDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ModalidadeService {
    
    public Page<Modalidade> findAll(Pageable pageable);
    public Modalidade findById(Long id);
    public Modalidade saveModalidade(ModalidadeDto ModalidadeDto);
    public void delete(Modalidade Modalidade);
    public Modalidade update(Long id, ModalidadeDto ModalidadeDto);
}
