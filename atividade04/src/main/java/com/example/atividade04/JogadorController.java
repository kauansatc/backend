package com.example.atividade04;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JogadorController {
    @GetMapping("/novo-jogador")
    public Jogador NovoJogador() throws Exception {
        var name = randomItem(Atividade04Application.Names);
        var surname = randomItem(Atividade04Application.Surnames);
        var club = randomItem(Atividade04Application.Clubs);
        var position = randomItem(Atividade04Application.Positions);
        var age = Jogador.randomIdade();

        return new Jogador(name, surname, age, club, position);
    }

    public static <T> T randomItem(T[] list) {
        return list[(int) Math.floor(Math.random() * list.length)];
    }
}
