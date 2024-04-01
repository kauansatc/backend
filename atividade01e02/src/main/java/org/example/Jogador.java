package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Jogador {
    String nome, sobrenome, clube, posicao;
    int idade;

    public Jogador(String nome, String sobrenome, int idade, String clube, String posicao){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.clube = clube;
        this.posicao = posicao;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public String getClube() {
        return clube;
    }

    public String getPosicao() {
        return posicao;
    }

    public String getDescricao() {
        return "%s %s é um futebolista brasileiro de %d anos que atua como %s. Atualmente defende o %s"
                .formatted(nome, sobrenome, idade, posicao, clube);
    }

    public static Jogador random() throws Exception {
        String nome = Main.randomItem(Main.requestData("nomes")) ;
        String sobrenome = Main.randomItem(Main.requestData("sobrenomes")) ;
        String clube = Main.randomItem(Main.requestData("clubes")) ;
        String posicao = Main.randomItem(Main.requestData("posicoes")) ;
        int idade = randomIdade();

        return new Jogador(nome, sobrenome, idade, clube, posicao);
    }

    public static Jogador[] random(int gerarNJogadores) throws Exception {
        String[] nomes = Main.requestData("nomes");
        String[] sobrenomes = Main.requestData("sobrenomes");
        String[] clubes = Main.requestData("clubes");
        String[] posicoes = Main.requestData("posicoes");

        return IntStream.range(0, gerarNJogadores).mapToObj(i -> new Jogador(
                Main.randomItem(nomes),
                Main.randomItem(sobrenomes),
                Jogador.randomIdade(),
                Main.randomItem(clubes),
                Main.randomItem(posicoes)
        )).toArray(Jogador[]::new);
    }

    static int randomIdade()
    {
        return 17 + (int)(Math.random() * (40 - 17));
    }

}
