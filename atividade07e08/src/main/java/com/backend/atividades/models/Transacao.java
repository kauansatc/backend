package com.backend.atividades.models;

public class Transacao {
    private String recebedor;
    private String pagador;
    private Double quantidade;

    public String getPagador() {
        return pagador;
    }

    public String getRecebedor() {
        return recebedor;
    }

    public Double getQuantidade() {
        return quantidade;
    }
}
