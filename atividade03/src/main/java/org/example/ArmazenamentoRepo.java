package org.example;

import java.util.ArrayList;

public interface ArmazenamentoRepo {
    void armazenar(String texto);
    ArrayList<Indicacao> recuperar() throws Exception;
}
