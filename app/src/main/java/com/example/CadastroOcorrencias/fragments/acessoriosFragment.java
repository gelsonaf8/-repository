package com.example.CadastroOcorrencias.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.adapter.AcessoriosAdapter;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Acessorios;

import java.util.ArrayList;
import java.util.List;


public class acessoriosFragment extends Fragment {

RecyclerView recyclerView;
List<Acessorios> ListaAcessorios;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_acessorios_viatura, container, false);

        recyclerView = view.findViewById(R.id.recyclerfragmentInformacoes);
        CarregarListaDeAcessorios();


        return view;

    }

    public void CarregarListaDeAcessorios(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(getContext());

        Cursor cursor =myDB.readAllData("Acessorios","AC");
        ListaAcessorios = new ArrayList<>();

        while (cursor.moveToNext()){
            Acessorios acessorios = new Acessorios();

            acessorios.setId(cursor.getInt(0));
            acessorios.setDescricao(cursor.getString(1));
            acessorios.setSim(cursor.getString(2));
            acessorios.setCategoria(cursor.getString(3));

            ListaAcessorios.add(acessorios);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        AcessoriosAdapter acessoriosAdapter = new AcessoriosAdapter(ListaAcessorios,getContext(), getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(acessoriosAdapter);

    }
}