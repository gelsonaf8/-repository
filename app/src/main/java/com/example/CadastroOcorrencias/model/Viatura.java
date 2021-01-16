package com.example.CadastroOcorrencias.model;

import android.content.Context;
import android.database.Cursor;

import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;

import java.io.Serializable;

public class Viatura implements Serializable {
    //Informações
    private int _id;
    private String Prefixo;
    private String Modalidade;
    private String setor;
    private String KmInicio;
    private String KmTermino;

    //Outros
    private String CombustivelAssumido,CombustivelPassado,AcabamentoInterno,Obs;

    public Viatura() {

    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPrefixo() {
        return Prefixo;
    }

    public void setPrefixo(String prefixo) {
        Prefixo = prefixo;
    }

    public String getModalidade() {
        return Modalidade;
    }

    public void setModalidade(String modalidade) {
        Modalidade = modalidade;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getKmInicio() {
        return KmInicio;
    }

    public void setKmInicio(String kmInicio) {
        KmInicio = kmInicio;
    }

    public String getKmTermino() {
        return KmTermino;
    }

    public void setKmTermino(String kmTermino) {
        KmTermino = kmTermino;
    }


    public String getCombustivelAssumido() {
        return CombustivelAssumido;
    }

    public void setCombustivelAssumido(String combustivelAssumido) {
        CombustivelAssumido = combustivelAssumido;
    }

    public String getCombustivelPassado() {
        return CombustivelPassado;
    }

    public void setCombustivelPassado(String combustivelPassado) {
        CombustivelPassado = combustivelPassado;
    }

    public String getAcabamentoInterno() {
        return AcabamentoInterno;
    }

    public void setAcabamentoInterno(String acabamentoInterno) {
        AcabamentoInterno = acabamentoInterno;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String obs) {
        Obs = obs;
    }



    }
