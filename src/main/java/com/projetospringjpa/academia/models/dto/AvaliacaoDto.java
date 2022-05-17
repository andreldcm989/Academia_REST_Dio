package com.projetospringjpa.academia.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDto {
    
    private Long alunoId;
    private Double peso;
    private Double altura;

}
