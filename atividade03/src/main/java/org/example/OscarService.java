package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OscarService {
    ArrayList<Indicacao> indicacoes = new ArrayList<>();
    @Autowired
    ArmazenamentoRepo armazenamentoRepo;

    public void recuperarArmazenamento() {
        try {
            indicacoes = armazenamentoRepo.recuperar();
        }
        catch (Exception e){
            System.out.println("sem armazenamento para recuperar...");
        }
    }

    public void addIndicacao(Indicacao indicacao){
        Indicavel indicavel = indicacao.getIndicavel();
        indicavel.setNIndicacoes((short) (indicavel.getNIndicacoes() + 1));


        String complemento = "";

        if (indicavel instanceof Filme)
            complemento = ((Filme) indicavel).getGenero();
        else {
            complemento = ((Ator) indicavel).getNacionalidade();
        }

        String line = "%s;\t%s;\t%s;\t%s;\t%n".formatted(
                indicavel.getClass().toString().split(" ")[1],
                indicavel.getNome(),
                complemento,
                indicacao.getCategoria()
        );

        this.indicacoes.add(indicacao);
        armazenamentoRepo.armazenar(line);
    }

    public void mostrarListaIndicados() throws Exception {
        for(Indicacao ind: indicacoes){
            System.out.printf("%s indicado à %s%n", ind.getIndicavel().getNome(), ind.getCategoria());
        }
    }
}
