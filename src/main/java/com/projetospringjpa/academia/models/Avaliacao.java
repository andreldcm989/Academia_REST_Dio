package com.projetospringjpa.academia.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_avaliacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dtAvaliacao = LocalDateTime.now();
    private Double peso;
    private Double altura;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    
    
}
