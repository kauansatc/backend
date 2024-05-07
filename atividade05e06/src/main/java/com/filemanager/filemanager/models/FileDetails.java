package com.filemanager.filemanager.models;

import java.sql.Date;
import java.util.UUID;

public class FileDetails {
    public FileDetails(FileUploadRequest request) {
        this.id = UUID.randomUUID().toString();
        this.nome = request.getNome();
        this.conteudo = request.getConteudo();
        this.tamanho = String.valueOf(request.getConteudo().length());
        this.tipo = request.getTipo();
        this.dataCriacao = String.valueOf(new Date(System.currentTimeMillis()));
        this.dataModificacao = String.valueOf(new Date(System.currentTimeMillis()));
        this.diretorio = request.getDiretorio();
    }

    private String id;

    private String nome;

    private String conteudo;

    private String tamanho;

    private String tipo;

    private String dataCriacao;

    private String dataModificacao;

    private String diretorio;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public String getDataModificacao() {
        return dataModificacao;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataModificacao(String dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}