package com.filemanager.filemanager.models;

import java.util.Date;

public class FileShareRequest {
    private String idArquivo;
    private Date validade;

    public String getIdArquivo() {
        return idArquivo;
    }

    public Date getValidade() {
        return validade;
    }
}
