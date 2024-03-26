package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Ator implements Indicavel {
    private boolean elegivel;
    private short nIndicacoes;
    private String nome;
    private String nacionalidade;

    public Ator(String nome, String nacionalidade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    @Override
    public boolean getElegivel() {
        return this.elegivel;
    }

    @Override
    public void setElegivel(boolean elegivel) {
        this.elegivel = elegivel;
    }

    @Override
    public short getNIndicacoes() {
        return this.nIndicacoes;
    }

    @Override
    public void setNIndicacoes(short nIndicacoes) {
        this.nIndicacoes  = nIndicacoes;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
