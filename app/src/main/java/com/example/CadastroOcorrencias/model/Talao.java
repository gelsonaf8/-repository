package com.example.CadastroOcorrencias.model;

import java.io.Serializable;

public class Talao implements Serializable {
    private int id;
    private String ntalao;
    private String qtrInicio ;
    private String qtrLocal ;
    private String qtr1Term ;
    private String qtrFinal;
    private String kmInicio;
    private String kmLocal ;
    private String km1Term ;
    private String kmFinal ;
    private String endereco;
    private String natureza ;
    private String resultado;
    private String observacao;
    private String data;
    private String arquivado;



    public String getArquivado() {
        return arquivado;
    }

    public void setArquivado(String aquivado) {
        this.arquivado = aquivado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Talao() {
    }


    public String getQtrLocal() {
        return qtrLocal;
    }

    public void setQtrLocal(String qtrLocal) {
        this.qtrLocal = qtrLocal;
    }

    public String getQtr1Term() {
        return qtr1Term;
    }

    public void setQtr1Term(String qtr1Term) {
        this.qtr1Term = qtr1Term;
    }

    public String getKmLocal() {
        return kmLocal;
    }

    public void setKmLocal(String kmLocal) {
        this.kmLocal = kmLocal;
    }

    public String getKm1Term() {
        return km1Term;
    }

    public void setKm1Term(String km1Term) {
        this.km1Term = km1Term;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNtalao() {
        return ntalao;
    }

    public void setNtalao(String ntalao) {
        this.ntalao = ntalao;
    }

    public String getQtrInicio() {
        return qtrInicio;
    }

    public void setQtrInicio(String qtrInicio) {
        this.qtrInicio = qtrInicio;
    }

    public String getQtrFinal() {
        return qtrFinal;
    }

    public void setQtrFinal(String qtrFinal) {
        this.qtrFinal = qtrFinal;
    }

    public String getKmInicio() {
        return kmInicio;
    }

    public void setKmInicio(String kmInicio) {
        this.kmInicio = kmInicio;
    }

    public String getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(String kmFinal) {
        this.kmFinal = kmFinal;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



}
