package org.example;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@Repository
public class TxtArmazenamentoRepo implements ArmazenamentoRepo {
    @Override
    public void armazenar(String texto) {
        try {
            FileOutputStream arquivo = new FileOutputStream("data.csv", true);
            PrintWriter printWriter = new PrintWriter(arquivo);
            printWriter.append(texto);
            printWriter.close();
        } catch (Exception exception) {
            System.out.println("Falha ao abrir arquivo");
        }
    }

    @Override
    public ArrayList<Indicacao> recuperar() throws Exception {
        ArrayList<Indicacao> res = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("data.csv"));
        String line = reader.readLine();

        while (line != null)
        {
            if(line == null) continue;
            var split = line.split(";\t");

            var className = split[0];

            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(String.class, String.class);
            Object instance = constructor.newInstance(split[1], split[2]);
            Indicavel indicavel = (Indicavel)instance;
            indicavel.setElegivel(true);

            Indicacao indicacao = new Indicacao(indicavel, split[3]);
            res.add(indicacao);
            line = reader.readLine();
        }

        reader.close();

        return res;
    }
}
