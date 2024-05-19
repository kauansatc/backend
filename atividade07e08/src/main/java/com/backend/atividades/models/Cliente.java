package com.backend.atividades.models;

public class Cliente {
    private String nome;
    private Double saldo;
    private String senha;

    public Cliente(String nome, Double saldo, String senha) {
        this.nome = nome;
        this.saldo = saldo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public String getSenha() {
        return senha;
    }
}
