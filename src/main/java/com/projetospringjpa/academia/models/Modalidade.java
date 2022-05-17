package com.projetospringjpa.academia.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_modalidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modalidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modalidade", nullable = false)
    private Long id;
    @Column(name = "modalidade", nullable = false)
    private String nome;
    @Column(nullable = false)
    private String dtCriacao;

}
