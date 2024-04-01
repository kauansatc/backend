package org.example;

import java.net.URI;
import java.net.http.*;

public class Main {
    public static void main(String[] args) throws Exception{
        int gerarNJoagodres = 12;

        for(Jogador jogador : Jogador.random(gerarNJoagodres)){
            System.out.println(jogador.getDescricao());
        }
    }

    public static <T> T randomItem(T[] list)
    {
        return list[(int) Math.floor(Math.random() * list.length)];
    }

    public static String[] requestData(String route) throws Exception
    {
        URI uri = URI.create("https://venson.net.br/resources/data/%s.txt".formatted(route));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // System.out.println(response);
        return response.body().split("\n");
    }
}