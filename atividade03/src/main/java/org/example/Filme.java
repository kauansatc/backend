package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Filme implements Indicavel {
    private boolean elegivel;
    private short nIndicacoes;
    private String nome;
    private String genero;
    public  Filme(String nome, String genero){
        this.nome = nome;
        this.genero = genero;
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
        this.nIndicacoes = nIndicacoes;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
