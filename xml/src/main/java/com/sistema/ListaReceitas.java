package com.sistema;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@XmlRootElement(name = "receitas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaReceitas {

    @XmlElement(name = "receita")
    private List<Receita> receitas = new ArrayList<>();

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }
}