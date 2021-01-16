package com.example.CadastroOcorrencias.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.model.Talao;

import java.util.ArrayList;
import java.util.List;

public class Tela_Inicial extends AppCompatActivity{
            private CardView btn_taloes,btn_abordados,btn_historico,btn_cadastro,btn_Relatorio,btn_Equipe,btn_Viatura,btn_GerarPDF,btnListaCarater;
            List<Talao> listaTalao = new ArrayList<>();
            String textoRelatorio;
            //MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));
        btn_taloes = findViewById(R.id.btnTaloes);
        btn_cadastro = findViewById(R.id.btnCadastro);
        btn_abordados = findViewById(R.id.btnAbordados);
        btn_historico = findViewById(R.id.btnArquivados);
        btn_Relatorio = findViewById(R.id.btnRelatorio);
        btn_Equipe = findViewById(R.id.btnEquipe);
        btn_Viatura = findViewById(R.id.btnViatura);
        btn_GerarPDF = findViewById(R.id.btnGerarPDF);
        btnListaCarater = findViewById(R.id.btnCarater);


        btn_taloes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ocorrencias_Inicial.class);
                startActivity(intent);

            }
        });

        /*btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cadastrar_Enderecos.class);
                startActivity(intent);

            }
        });*/

        btn_abordados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Pessoas_Interresse_Inicial.class);
                startActivity(intent);

            }
        });


        btn_historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ocorrencias_Inicial.class);
                intent.putExtra("isActive", false);
                startActivity(intent);

            }
        });
        btn_Relatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Relatorio_Inicial.class);
                startActivity(intent);
            }
        });
       btn_Equipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Inicial.this, Equipe_Inicial.class);
                startActivity(intent);
            }
        });
        btn_Viatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Inicial.this, ViaturaActivity.class);
                startActivity(intent);
            }
        });

        btn_GerarPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Inicial.this, GerarPdf.class);
                startActivity(intent);
            }
        });
        btnListaCarater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Inicial.this, Carater_Inicial.class);
                startActivity(intent);
            }
        });


    }




    };


