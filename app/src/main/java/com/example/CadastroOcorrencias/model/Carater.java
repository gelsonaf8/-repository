package com.example.CadastroOcorrencias.model;

import java.io.Serializable;

public class Carater implements Serializable {
    String Numeros;
    String Letras;
    String Modelo;
    String Cor;
    String Ano;
    String Area;
    String Natureza;
    int id;

    public Carater() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeros() {
        return Numeros;
    }

    public void setNumeros(String numeros) {
        Numeros = numeros;
    }

    public String getLetras() {
        return Letras;
    }

    public void setLetras(String letras) {
        Letras = letras;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getCor() {
        return Cor;
    }

    public void setCor(String cor) {
        Cor = cor;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String ano) {
        Ano = ano;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getNatureza() {
        return Natureza;
    }

    public void setNatureza(String natureza) {
        Natureza = natureza;
    }
}
