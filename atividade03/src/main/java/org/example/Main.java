package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        OscarService oscarService = context.getBean(OscarService.class);
        oscarService.recuperarArmazenamento();

        Filme filme_vovozona3 = context.getBean(Filme.class, "Vovózona 3", "Comédia");
        filme_vovozona3.setElegivel(true);
        Ator adam_sandler = context.getBean(Ator.class, "Adam Sandler", "Marciano");
        adam_sandler.setElegivel(true);

        Indicacao item1 = context.getBean(Indicacao.class, filme_vovozona3, "Melhor Filme");
        Indicacao item2 = context.getBean(Indicacao.class, adam_sandler, "Melhor Cara");

        oscarService.addIndicacao(item1);
        oscarService.addIndicacao(item2);

        oscarService.mostrarListaIndicados();
    }
}