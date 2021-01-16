package com.example.CadastroOcorrencias.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Equipe_Inicial extends AppCompatActivity {
    CardView cardEncarregado,cardMotorista,cardAux1,cardAux2;
    TextView txtGradEnc,txtReEnc,txtNomeEnc,txtTelEnc,txtGradMot,txtReMot,txtNomeMot,txtTelMot;
    TextView txtGradAux1,txtReAux1,txtNomeAux1,txtTelAux1,txtGradAux2,txtReAux2,txtNomeAux2,txtTelAux2;
    List<com.example.CadastroOcorrencias.model.Equipe> ListaEquipe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));
        setContentView(R.layout.activity_add_equipe);
        cardEncarregado = findViewById(R.id.cardEncarregado);
        cardMotorista = findViewById(R.id.cardMotorista);
        cardAux1 = findViewById(R.id.cardAuxiliar1);
        cardAux2 = findViewById(R.id.cardAuxiliar2);

        txtGradEnc = findViewById(R.id.txtPostEnc);
        txtReEnc = findViewById(R.id.txtReEnc);
        txtNomeEnc = findViewById(R.id.txtNomeEnc);
        txtTelEnc = findViewById(R.id.txtTelEnc);

        txtGradMot = findViewById(R.id.txtPostMot);
        txtReMot = findViewById(R.id.txtReMot);
        txtNomeMot = findViewById(R.id.txtNomeMot);
        txtTelMot = findViewById(R.id.txtTelMot);

        txtGradAux1 = findViewById(R.id.txtPostAux1);
        txtReAux1 = findViewById(R.id.txtReAux1);
        txtNomeAux1 = findViewById(R.id.txtNomeAux1);
        txtTelAux1 = findViewById(R.id.txtTelAux1);

        txtGradAux2 = findViewById(R.id.txtPostAux2);
        txtReAux2 = findViewById(R.id.txtReAux2);
        txtNomeAux2 = findViewById(R.id.txtNomeAux2);
        txtTelAux2 = findViewById(R.id.txtTelAux2);




        carregarEquipe();

        cardEncarregado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(Equipe_Inicial.this, Cadastrar_Equipe.class);
                intent.putExtra("Equipe",ListaEquipe.get(0));
                finish();
                startActivity(intent);
            }
        });

        cardMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(Equipe_Inicial.this, Cadastrar_Equipe.class);
                intent.putExtra("Equipe",ListaEquipe.get(1));
                finish();
                startActivity(intent);
            }});

        cardAux1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(Equipe_Inicial.this, Cadastrar_Equipe.class);
                intent.putExtra("Equipe",ListaEquipe.get(2));
                finish();
                startActivity(intent);
            }
        });

        cardAux2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =  new Intent(Equipe_Inicial.this, Cadastrar_Equipe.class);
                intent.putExtra("Equipe",ListaEquipe.get(3));
                finish();
                startActivity(intent);
            }
        });

    }



    public void carregarEquipe(){



        MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
        Cursor cursor  = myDB.readAllData("Equipe","T");

        while (cursor.moveToNext()){
            com.example.CadastroOcorrencias.model.Equipe equipe = new com.example.CadastroOcorrencias.model.Equipe();
            equipe.setId(cursor.getInt(0));
            equipe.setGraduacao(cursor.getString(1));
            equipe.setRE(cursor.getString(2));
            equipe.setNome(cursor.getString(3));
            equipe.setTelefone(cursor.getString(4));
            equipe.setFuncao(cursor.getString(5));
            ListaEquipe.add(equipe);
        }


        txtGradEnc.setText(ListaEquipe.get(0).getGraduacao());
        txtReEnc.setText(ListaEquipe.get(0).getRE());
        txtNomeEnc.setText(ListaEquipe.get(0).getNome());
        txtTelEnc.setText(ListaEquipe.get(0).getTelefone());

        txtGradMot.setText(ListaEquipe.get(1).getGraduacao());
        txtReMot.setText(ListaEquipe.get(1).getRE());
        txtNomeMot.setText(ListaEquipe.get(1).getNome());
        txtTelMot.setText(ListaEquipe.get(1).getTelefone());

        txtGradAux1.setText(ListaEquipe.get(2).getGraduacao());
        txtReAux1.setText(ListaEquipe.get(2).getRE());
        txtNomeAux1.setText(ListaEquipe.get(2).getNome());
        txtTelAux1.setText(ListaEquipe.get(2).getTelefone());

        txtGradAux2.setText(ListaEquipe.get(3).getGraduacao());
        txtReAux2.setText(ListaEquipe.get(3).getRE());
        txtNomeAux2.setText(ListaEquipe.get(3).getNome());
        txtTelAux2.setText(ListaEquipe.get(3).getTelefone());


    }
}