package com.example.CadastroOcorrencias.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class Cadastrar_Equipe extends AppCompatActivity {
    TextInputEditText inputGrad,inputRe,inputNome,inputTelefone;
    FloatingActionButton btnAddEquipe;
    TextView txtTitulo;
    com.example.CadastroOcorrencias.model.Equipe equipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_equipe);
        txtTitulo = findViewById(R.id.txtTitulo);
        inputGrad = findViewById(R.id.inputGrad);
        inputRe = findViewById(R.id.inputRe);
        inputNome = findViewById(R.id.inputNome);
        inputTelefone = findViewById(R.id.inputTelefone);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));

        btnAddEquipe = findViewById(R.id.btnAddEquipe);
        getAndSetIntentData();

        btnAddEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDb = new MyDatabaseHelper(Cadastrar_Equipe.this);

                equipe.setGraduacao(inputGrad.getText().toString().trim());
                equipe.setRE(inputRe.getText().toString().trim());
                equipe.setNome(inputNome.getText().toString().trim());
                equipe.setTelefone(inputTelefone.getText().toString().trim());
                myDb.atualizarEquipe(equipe,String.valueOf(equipe.getId()));

                Intent intent = new Intent(Cadastrar_Equipe.this, Equipe_Inicial.class);
                finish();
                startActivity(intent);
            }
        });


    }





    void getAndSetIntentData() {
        Bundle dados = getIntent().getExtras();

       equipe = (com.example.CadastroOcorrencias.model.Equipe) dados.getSerializable("Equipe");

        //Colocando dados nos Inputs
        txtTitulo.setText(equipe.getFuncao());
        inputGrad.setText(equipe.getGraduacao());
        inputRe.setText(equipe.getRE());
        inputNome.setText(equipe.getNome());
        inputTelefone.setText(equipe.getTelefone());




    }
}