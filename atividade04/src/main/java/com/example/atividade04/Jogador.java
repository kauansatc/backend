package com.example.atividade04;

public class Jogador {
    String nome, sobrenome, clube, posicao;
    int idade;

    public Jogador(String nome, String sobrenome, int idade, String clube, String posicao) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.clube = clube;
        this.posicao = posicao;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public String getClube() {
        return clube;
    }

    public String getPosicao() {
        return posicao;
    }

    static int randomIdade() {
        return 17 + (int) (Math.random() * (40 - 17));
    }
}