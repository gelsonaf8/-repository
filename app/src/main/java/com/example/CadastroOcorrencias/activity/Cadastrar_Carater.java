package com.example.CadastroOcorrencias.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Carater;
import com.example.CadastroOcorrencias.model.Talao;

public class Cadastrar_Carater extends AppCompatActivity {
    Carater carater;
    TextView txtCaraterNumeros, txtCaraterLetras,txtCaraterModelo,txtCaraterCor,txtCaraterAno,txtCaraterArea,txtCaraterRF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar__carater);
        txtCaraterNumeros = findViewById(R.id.txtCaraterNumero);
        txtCaraterLetras = findViewById(R.id.txtCaraterLetras);
        txtCaraterModelo = findViewById(R.id.txtCaraterModelo);
        txtCaraterCor = findViewById(R.id.txtCaraterCor);
        txtCaraterAno = findViewById(R.id.txtCaraterAno);
        txtCaraterArea = findViewById(R.id.txtCaraterArea);
        txtCaraterRF = findViewById(R.id.txtCaraterRF);
        getAndSetIntentData();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_carater,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Salvar_carater:
                MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
                carater.setNumeros(txtCaraterNumeros.getText().toString());
                carater.setLetras(txtCaraterLetras.getText().toString());
                carater.setModelo(txtCaraterModelo.getText().toString());
                carater.setCor(txtCaraterCor.getText().toString());
                carater.setAno(txtCaraterAno.getText().toString());
                carater.setArea(txtCaraterArea.getText().toString());
                carater.setNatureza(txtCaraterRF.getText().toString());

                myDB.atualizarCarater(carater,String.valueOf(carater.getId()));
                Intent intent = new Intent(Cadastrar_Carater.this,Carater_Inicial.class);
                finish();
                startActivity(intent);

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    void getAndSetIntentData() {
        Bundle dados = getIntent().getExtras();
        carater = (Carater) dados.getSerializable("Carater");

        //Colocando dados nos Inputs
        txtCaraterNumeros.setText(carater.getNumeros());
        txtCaraterLetras.setText(carater.getLetras());
        txtCaraterModelo.setText(carater.getModelo());
        txtCaraterCor.setText(carater.getCor());
        txtCaraterAno.setText(carater.getAno());
        txtCaraterArea.setText(carater.getArea());
        txtCaraterRF.setText(carater.getNatureza());


    }
}