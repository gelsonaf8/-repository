package com.example.CadastroOcorrencias.model;

import android.database.Cursor;

import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipe implements Serializable {

    private int id;
    private String Graduacao;
    private String RE;
    private String Nome;
    private String Telefone;
    private String Funcao;

    public Equipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGraduacao() {
        return Graduacao;
    }

    public void setGraduacao(String graduacao) {
        Graduacao = graduacao;
    }

    public String getRE() {
        return RE;
    }

    public void setRE(String RE) {
        this.RE = RE;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getFuncao() {
        return Funcao;
    }

    public void setFuncao(String funcao) {
        Funcao = funcao;
    }




        }
