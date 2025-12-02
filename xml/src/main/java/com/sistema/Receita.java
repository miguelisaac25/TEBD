package com.sistema;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "receita")
@XmlAccessorType(XmlAccessType.FIELD)
public class Receita {

    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "titulo")
    private String titulo;

    @XmlElement(name = "descricao")
    private String descricao;

    @XmlElement(name = "ingredientes")
    private String ingredientes;

    @XmlElement(name = "modo_preparo")
    private String modoPreparo;

    @XmlElement(name = "emoji_foto")
    private String emojiFoto;

    @XmlElement(name = "image_url")
    private String imageUrl;

    public Receita() {
    }

    public Receita(int id, String titulo, String descricao, String ingredientes, String modoPreparo, String emojiFoto,
            String imageUrl) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.ingredientes = ingredientes;
        this.modoPreparo = modoPreparo;
        this.emojiFoto = emojiFoto;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public String getEmojiFoto() {
        return emojiFoto;
    }

    public void setEmojiFoto(String emojiFoto) {
        this.emojiFoto = emojiFoto;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Receita: " + titulo + " - " + descricao;
    }
}