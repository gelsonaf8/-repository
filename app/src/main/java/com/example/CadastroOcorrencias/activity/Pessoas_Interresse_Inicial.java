package com.example.CadastroOcorrencias.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.adapter.AbordadoAdapter;
import com.example.CadastroOcorrencias.helper.AbordadoDAO;
import com.example.CadastroOcorrencias.model.Abordado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Pessoas_Interresse_Inicial extends AppCompatActivity {
    FloatingActionButton floatAbordados;
    RecyclerView recyclerAbordados;
    List<Abordado> ListaAbordados = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_abordados);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));

        floatAbordados = findViewById(R.id.floatAddAbordados);
        recyclerAbordados = findViewById(R.id.recyclerAbordados);


        floatAbordados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cadastrar_Pessoas_Interresse.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        CarregarDados();
    }

    public void CarregarDados(){
        AbordadoDAO abordadoDAO = new AbordadoDAO(getApplicationContext());
        ListaAbordados = abordadoDAO.RecuperarAbordados();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        AbordadoAdapter abordadoAdapter = new AbordadoAdapter(this,getApplicationContext(),ListaAbordados);
        recyclerAbordados.setLayoutManager(layoutManager);
        recyclerAbordados.setHasFixedSize(true);
        recyclerAbordados.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerAbordados.setAdapter(abordadoAdapter);
    }
}