package com.example.CadastroOcorrencias.model;

public class Avaria {

    private int _id;
    private String descricao;

    public Avaria() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
