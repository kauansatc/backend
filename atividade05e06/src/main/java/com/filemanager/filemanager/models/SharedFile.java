package com.filemanager.filemanager.models;

import java.util.Date;
import java.util.UUID;

public class SharedFile {
    private String id;
    private String idArquivo;
    private Date validade;

    public SharedFile(FileShareRequest request) {
        this.id = UUID.randomUUID().toString();
        this.idArquivo = request.getIdArquivo();
        this.validade = request.getValidade();
    }

    public String getId() {
        return id;
    }

    public String getIdArquivo() {
        return idArquivo;
    }

    public Date getValidade() {
        return validade;
    }
}
