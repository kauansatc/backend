package com.filemanager.filemanager.models;

public class DirectoryContent {
    private String diretorio;
    private FileDetails[] conteudo;

    public DirectoryContent(String diretorio, FileDetails[] conteudo) {
        this.diretorio = diretorio;
        this.conteudo = conteudo;
    }

    public FileDetails[] getConteudo() {
        return conteudo;
    }

    public String getDiretorio() {
        return diretorio;
    }
}
