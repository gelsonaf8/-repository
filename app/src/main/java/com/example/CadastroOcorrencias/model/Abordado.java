package com.example.CadastroOcorrencias.model;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Abordado implements Serializable {
    private int id;
    private byte[] foto;
    private String nome;
    private String rg;
    private  String enderecoAbordagem;
    private String atrAbordagem;
    private String dataAbordagem;
    private String artigos;
    private String tatuagem;
    private String vulgo;
    private byte[] tatoo1;
    private byte[] tatoo2;
    private byte[] tatoo3;
    private byte[] tatoo4;
    private byte[] tatoo5;
    private byte[] tatoo6;

    public String getDataAbordagem() {
        return dataAbordagem;
    }

    public void setDataAbordagem(String dataAbordagem) {
        this.dataAbordagem = dataAbordagem;
    }

    public byte[] getTatoo1() {
        return tatoo1;
    }

    public void setTatoo1(byte[] tatoo1) {
        this.tatoo1 = tatoo1;
    }

    public byte[] getTatoo2() {
        return tatoo2;
    }

    public void setTatoo2(byte[] tatoo2) {
        this.tatoo2 = tatoo2;
    }

    public byte[] getTatoo3() {
        return tatoo3;
    }

    public void setTatoo3(byte[] tatoo3) {
        this.tatoo3 = tatoo3;
    }

    public byte[] getTatoo4() {
        return tatoo4;
    }

    public void setTatoo4(byte[] tatoo4) {
        this.tatoo4 = tatoo4;
    }

    public byte[] getTatoo5() {
        return tatoo5;
    }

    public void setTatoo5(byte[] tatoo5) {
        this.tatoo5 = tatoo5;
    }

    public byte[] getTatoo6() {
        return tatoo6;
    }

    public void setTatoo6(byte[] tatoo6) {
        this.tatoo6 = tatoo6;
    }

    private String observacao;

    public String getVulgo() {
        return vulgo;
    }

    public void setVulgo(String vulgo) {
        this.vulgo = vulgo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEnderecoAbordagem() {
        return enderecoAbordagem;
    }

    public void setEnderecoAbordagem(String enderecoAbordagem) {
        this.enderecoAbordagem = enderecoAbordagem;
    }

    public String getAtrAbordagem() {
        return atrAbordagem;
    }

    public void setAtrAbordagem(String atrAbordagem) {
        this.atrAbordagem = atrAbordagem;
    }

    public String getArtigos() {
        return artigos;
    }

    public void setArtigos(String artigos) {
        this.artigos = artigos;
    }

    public String getTatuagem() {
        return tatuagem;
    }

    public void setTatuagem(String tatuagem) {
        this.tatuagem = tatuagem;
    }




}


