package com.filemanager.filemanager.models;

public class FileUploadRequest {
    private String nome;
    private String conteudo;
    private String diretorio;
    private String tipo;

    // Getters and setters for each field

    public String getNome() {
        return nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public String getTipo() {
        return tipo;
    }
}