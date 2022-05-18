package com.projetospringjpa.academia.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDto {
    
    private Long idAluno;
    private Long idTurma;

    
}
