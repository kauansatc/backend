package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Indicacao {
    private Indicavel indicavel;
    private String categoria;

    public Indicacao(Indicavel indicavel, String categoria) throws Exception {
        if(!indicavel.getElegivel())
            throw new Exception("Tentativa de indicar candidato inelegível");
        this.indicavel = indicavel;
        this.categoria = categoria;
    }

    public Indicavel getIndicavel() {
        return indicavel;
    }

    public void setIndicavel(Indicavel indicavel) {
        this.indicavel = indicavel;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
