package com.example.atividade04;

import java.net.URI;
import java.net.http.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Atividade04Application {
	static String[] Names, Surnames, Clubs, Positions;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Atividade04Application.class, args);

		Names = requestData("nomes");
		Surnames = requestData("sobrenomes");
		Clubs = requestData("clubes");
		Positions = requestData("posicoes");
	}

	public static String[] requestData(String route) throws Exception {
		URI uri = URI.create("https://venson.net.br/resources/data/%s.txt".formatted(route));
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String responseString = response.body();
		responseString = responseString.replace("\"", "");
		return responseString.split("\n");
	}
}
