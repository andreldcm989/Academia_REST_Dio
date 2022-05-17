package com.projetospringjpa.academia.models.dto;

import java.time.LocalDate;

import com.projetospringjpa.academia.models.Aluno;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlunoUpdateDto {

    private String nome;
    private String bairro;
    private LocalDate dtNasc;

    public AlunoUpdateDto(Aluno aluno) {
        nome = aluno.getNome();
        bairro = aluno.getBairro();
        dtNasc = aluno.getDtNasc();
    }

}
