package com.projetospringjpa.academia.models.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDto {

    @NotEmpty(message = "Preencha o campo corretamente.")
    @Size(min = 3, max = 60, message = "'${ValidatedValue}' precisa ter entre {min} e {max} caracteres.")
    private String nome;
    @CPF(message = "'${validatedValue}' é inválido")
    private String cpf;

    private String bairro;
    @NotNull
    @Past(message = "Data '${ValidatedValue} deve ser menor que a data de hoje.")
    private LocalDate dtNasc;

}
