package com.projetospringjpa.academia.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_instrutores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instrutor implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instrutor", nullable = false)
    private Long id;
    @Column(name = "nome_instrutor", nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cpf;
    @Column(name = "formacao_instrutor", nullable = false)
    private String formacao;
    @Column(nullable = false)
    private LocalDateTime dtCadastro;

    @OneToMany(mappedBy = "instrutor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Turmas> turmas = new ArrayList<>();
}
