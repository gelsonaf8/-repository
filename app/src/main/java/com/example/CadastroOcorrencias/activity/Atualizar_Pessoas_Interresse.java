package com.example.CadastroOcorrencias.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Abordado;

import java.io.ByteArrayInputStream;

public class Atualizar_Pessoas_Interresse extends AppCompatActivity{
    Abordado abordado = new Abordado();
    ImageView foto,tatoo1,tatoo2,tatoo3,tatoo4,tatoo5,tatoo6;
    TextView nome,rg,vulgo,artigos,endereco,tatuagens,data,horas;
    LinearLayout linearLayout1,linearLayout2,linearLayout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_abordados);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));

        foto = findViewById(R.id.iv_foto);
        nome = findViewById(R.id.nome);
        rg = findViewById(R.id.rg);
        vulgo = findViewById(R.id.vulgo);
        artigos = findViewById(R.id.artigos);
        endereco = findViewById(R.id.endereco);
        data = findViewById(R.id.data);
        horas = findViewById(R.id.horas);
        tatuagens = findViewById(R.id.tatuagens);
        tatoo1 = findViewById(R.id.tatoo1);
        tatoo2 = findViewById(R.id.tatoo2);
        tatoo3 = findViewById(R.id.tatoo3);
        tatoo4 = findViewById(R.id.tatoo4);
        tatoo5 = findViewById(R.id.tatoo5);
        tatoo6 = findViewById(R.id.tatoo6);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout4 = findViewById(R.id.linearLayout4);

        getAndSetIntentData();


    }



    //Recuperando dados das intents e colocando nos inputs
    void getAndSetIntentData() {
        Bundle dados = getIntent().getExtras();
        abordado = (Abordado) dados.getSerializable("abordado");


        //Colocando dados nos Inputs


        if(abordado.getFoto()!=null){
            foto.setImageBitmap(FotoConvertida(abordado.getFoto()));
        }
       nome.setText("Nome: "+ abordado.getNome());
       rg.setText("RG: "+abordado.getRg());
       vulgo.setText("Vulgo:"+abordado.getVulgo());
       artigos.setText("Artigos: "+abordado.getArtigos());
       endereco.setText("End. Abordagem: "+ abordado.getEnderecoAbordagem());
       data.setText("Data: "+ abordado.getDataAbordagem());
       horas.setText("Horas: "+ abordado.getAtrAbordagem());
       tatuagens.setText("Tatuagens: "+abordado.getTatuagem());

       if(abordado.getTatoo1()!=null){
           linearLayout1.setVisibility(View.VISIBLE);
           tatoo1.setImageBitmap(FotoConvertida(abordado.getTatoo1()));
       }
        if(abordado.getTatoo1()!=null){
            linearLayout1.setVisibility(View.VISIBLE);
            tatoo1.setImageBitmap(FotoConvertida(abordado.getTatoo1()));
        }
        if(abordado.getTatoo2()!=null){
            tatoo2.setImageBitmap(FotoConvertida(abordado.getTatoo2()));
        }
        if(abordado.getTatoo3()!=null){
            linearLayout2.setVisibility(View.VISIBLE);
            tatoo3.setImageBitmap(FotoConvertida(abordado.getTatoo3()));
        }
        if(abordado.getTatoo4()!=null){
            tatoo4.setImageBitmap(FotoConvertida(abordado.getTatoo4()));
        }
        if(abordado.getTatoo5()!=null){
            linearLayout4.setVisibility(View.VISIBLE);
            tatoo4.setImageBitmap(FotoConvertida(abordado.getTatoo5()));
        }
        if(abordado.getTatoo6()!=null){
            tatoo4.setImageBitmap(FotoConvertida(abordado.getTatoo6()));
        }




    }


    public Bitmap FotoConvertida(byte[] fotoAbordado){

        ByteArrayInputStream imageStream = new ByteArrayInputStream(fotoAbordado);
        Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);

        return imageBitmap;
    }




    void confirmDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("APAGAR DADOS");
        builder.setMessage("Tem certeza que você quer apagar os dados?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Atualizar_Pessoas_Interresse.this);
                myDB.deleteOneRow(String.valueOf(abordado.getId()),"Abordados");

                //Atualizar a acticity
                Intent intent = new Intent(Atualizar_Pessoas_Interresse.this, Pessoas_Interresse_Inicial.class);
                finish();
                startActivity(intent);

            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.abordados_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.apagarDados:
                confirmDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



}