package com.example.CadastroOcorrencias.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Talao;

import java.util.ArrayList;
import java.util.List;


public class Relatorio_Inicial extends AppCompatActivity {
    EditText txtRSO;
    String relatorio  ="";

    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        txtRSO = findViewById(R.id.txtRelatorio);

        final MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
        final Cursor cursor  = myDB.readAllData("Relatorio","RSO");

        while (cursor.moveToNext()){
            relatorio = cursor.getString(1);
        }

        txtRSO.setText(relatorio);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_relatorio,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.resumoOcorrencias:
                MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
                List<Talao> ListaTalao= new ArrayList<>();
                Cursor cursor = myDB.readAllData("Taloes","A");
                while (cursor.moveToNext()){
                    Talao talao = new Talao();
                    talao.setNtalao(cursor.getString(1));
                    talao.setResultado(cursor.getString(13));
                    talao.setObservacao(cursor.getString(14));
                    ListaTalao.add(talao);

                }
                for(Talao t:ListaTalao){
                    String s = "*T-"+t.getNtalao()+" "+t.getResultado()+" "+t.getObservacao()+".\n";
                    //this is to get the the cursor position
                    int start =txtRSO.getSelectionStart();
                    txtRSO.getText().insert(start, s);
                }

                break;
            case R.id.salvaRelatorio:
                myDB = new MyDatabaseHelper(getApplicationContext());
                myDB.atualizarRelatorio(txtRSO.getText().toString(),"1");


        }
        return super.onOptionsItemSelected(item);
    }
}