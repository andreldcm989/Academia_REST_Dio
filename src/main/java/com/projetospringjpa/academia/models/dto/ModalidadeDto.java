package com.projetospringjpa.academia.models.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModalidadeDto {
    
    @NotEmpty
    private String nome;
}
