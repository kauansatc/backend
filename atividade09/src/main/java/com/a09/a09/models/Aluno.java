package com.a09.a09.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private Integer idade;
    private Integer sala;

    public Aluno() {
    }

    public Aluno(String nome, Integer idade, Integer sala) {
        this.nome = nome;
        this.idade = idade;
        this.sala = sala;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public Integer getSala() {
        return sala;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }
}