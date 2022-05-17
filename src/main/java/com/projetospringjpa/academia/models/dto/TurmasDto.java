package com.projetospringjpa.academia.models.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmasDto {
    
    private LocalTime horario;
    private Long duracao;
    private Long idModalidade;
    private Long idInstrutor;

    
}
