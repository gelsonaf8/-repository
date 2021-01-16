package com.example.CadastroOcorrencias.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Viatura;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class Cadastrar_Viatura extends AppCompatActivity {
    TextInputEditText txtCadastrarVtrPrefixo,txtCadastrarVtrModalidade,txtCadastrarVtrSetor,txtCadastrarVtrKmInicial,txtCadastrarVtrKmFinal;
    FloatingActionButton btnAtualizarDadosVtr;

    Viatura viatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_viatura);
       txtCadastrarVtrPrefixo = findViewById(R.id.txtCadastroVtrPrefixo);
        txtCadastrarVtrModalidade = findViewById(R.id.txtCadastroVtrModalidade);
        txtCadastrarVtrSetor = findViewById(R.id.txtCadastroVtrSetor);
        txtCadastrarVtrKmInicial = findViewById(R.id.txtCadastroVtrKmInicial);
        txtCadastrarVtrKmFinal = findViewById(R.id.txtCadastroVtrKmFinal);
        btnAtualizarDadosVtr = findViewById(R.id.btnAtualizarDadosVtr);

        getAndSetIntentData();

        btnAtualizarDadosVtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDb = new MyDatabaseHelper(Cadastrar_Viatura.this);
                viatura.setPrefixo(txtCadastrarVtrPrefixo.getText().toString().trim());
                viatura.setModalidade(txtCadastrarVtrModalidade.getText().toString().trim());
                viatura.setSetor(txtCadastrarVtrSetor.getText().toString().trim());
                viatura.setKmInicio(txtCadastrarVtrKmInicial.getText().toString().trim());
                viatura.setKmTermino(txtCadastrarVtrKmFinal.getText().toString().trim());
                myDb.atualizarViatura(viatura,String.valueOf(1));
                Intent intent = new Intent(Cadastrar_Viatura.this, Viatura_Inicial.class);
                finish();
                startActivity(intent);
            }
        });




    }





    void getAndSetIntentData() {
        Bundle dados = getIntent().getExtras();
        viatura = (Viatura) dados.getSerializable("Viatura");

        //Colocando dados nos Inputs

        txtCadastrarVtrPrefixo.setText(viatura.getPrefixo());
        txtCadastrarVtrModalidade.setText(viatura.getModalidade());
        txtCadastrarVtrSetor.setText(viatura.getSetor());
        txtCadastrarVtrKmInicial.setText(viatura.getKmInicio());
        txtCadastrarVtrKmFinal.setText(viatura.getKmTermino());

    }
}