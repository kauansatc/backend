package com.filemanager.filemanager.models;

public class Directory {
    private String nome;
    private String diretorioPai = ".";

    public String getNome() {
        return nome;
    }

    public String getDiretorioPai() {
        return diretorioPai;
    }

    public Directory(String nome, String diretorioPai) {
        this.nome = nome;
        this.diretorioPai = diretorioPai;
    }
}
