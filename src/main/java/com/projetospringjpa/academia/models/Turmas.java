package com.projetospringjpa.academia.models;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_turmas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turmas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurma;
    @Column(name = "hr_inicio", nullable = false)
    private LocalTime horario;
    @Column(nullable = false)
    private Long duracao;

    @ManyToOne
    @JoinColumn(name = "modalidade_id", nullable = false)
    private Modalidade modalidade;

    @ManyToOne
    @JoinColumn(name = "instrutor_id", nullable = true)
    private Instrutor instrutor;

    @ManyToMany
    @JoinTable(name = "tb_matricula", joinColumns = @JoinColumn(name = "turma_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunos = new ArrayList<>();

    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Matricula> matriculas = new ArrayList<>();

    public LocalTime hrTerminoAula(){
        return horario.plusMinutes(duracao);
    }
}
