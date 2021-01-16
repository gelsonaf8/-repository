package com.example.CadastroOcorrencias.model;

public class Acessorios {

    private int id;
    private String Descricao;
    private String Sim;
    private String Categoria;

    public Acessorios() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getSim() {
        return Sim;
    }

    public void setSim(String sim) {
        Sim = sim;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
}
