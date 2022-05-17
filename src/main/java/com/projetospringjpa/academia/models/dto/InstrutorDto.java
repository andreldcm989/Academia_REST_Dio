package com.projetospringjpa.academia.models.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstrutorDto {

    @NotEmpty
    private String nome;
    @NotEmpty
    private String cpf;
    @NotEmpty
    private String formacao;
}
