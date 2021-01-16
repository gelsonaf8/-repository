package com.example.CadastroOcorrencias.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.CadastroOcorrencias.adapter.CustomAdapter;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.TalaoDAO;
import com.example.CadastroOcorrencias.model.Talao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Ocorrencias_Inicial extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_Imageview;
    TextView sem_dados;
    MyDatabaseHelper myDB;
    ArrayList<Talao> listaDeTalao;
    CustomAdapter customAdapter;
    Boolean isActive = true;
    Bitmap cabecalho,scaledCabecalho,imagem1,scaledImagem1,imagem2,scaledImagem2,rodape,scaledRodape,relatorio1,relatorio2,relatorio3,scaledRelatorio1,
    scaledRelatorio2,scaledRelatorio3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_Imageview = findViewById(R.id.empty_imageview);
        sem_dados = findViewById(R.id.sem_dados);


        imagem1 = BitmapFactory.decodeResource(getResources(),R.drawable.imagem1);
        scaledImagem1 = Bitmap.createScaledBitmap(imagem1,1800,1160,false);
        imagem2 = BitmapFactory.decodeResource(getResources(),R.drawable.imagem2);
        scaledImagem2 = Bitmap.createScaledBitmap(imagem2,1800,1100,false);
        cabecalho = BitmapFactory.decodeResource(getResources(),R.drawable.cabecalho);
        scaledCabecalho = Bitmap.createScaledBitmap(cabecalho,1800,240,false);
        rodape = BitmapFactory.decodeResource(getResources(),R.drawable.rodape);
        scaledRodape = Bitmap.createScaledBitmap(rodape,1800,452,false);

        //relatorio1 = BitmapFactory.decodeResource(getResources(),R.drawable.relatorio_1);
        //scaledRelatorio1 = Bitmap.createScaledBitmap(relatorio1,1850,1062,false);
       // relatorio2 = BitmapFactory.decodeResource(getResources(),R.drawable.relatorio_2);
       // scaledRelatorio2 = Bitmap.createScaledBitmap(relatorio2,1855,1108,false);
        //relatorio3 = BitmapFactory.decodeResource(getResources(),R.drawable.relatorio_3);
        //scaledRelatorio3 = Bitmap.createScaledBitmap(relatorio3,1850,494,false);


        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);



        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ocorrencias_Inicial.this, Cadastrar_Ocorrencias.class);
                intent.putExtra("Atualizar",false);
                startActivity(intent);
                finish();
            }
        });

        myDB = new MyDatabaseHelper(Ocorrencias_Inicial.this);
        listaDeTalao = new ArrayList<>();

        isActive = getIntent().getBooleanExtra("isActive",true);

        storeDataInArray();

        customAdapter = new CustomAdapter(Ocorrencias_Inicial.this,this,listaDeTalao);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Ocorrencias_Inicial.this));
        recyclerView.setHasFixedSize(true);



    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }


    //pegar os dados do banco de dados e adicionar no array
    void storeDataInArray(){
        Cursor cursor = null;
        if(isActive){
            cursor = myDB.readAllData("Taloes","A");


        }else{
            cursor = myDB.readAllData("Taloes","I");
            add_button.setVisibility(View.GONE);
        }



            if(cursor.getCount() == 0){
                empty_Imageview.setVisibility(View.VISIBLE);
                sem_dados.setVisibility(View.VISIBLE);

            }else{
                while (cursor.moveToNext()){
                    Talao talao = new Talao();
                    talao.setId(cursor.getInt(0));
                   talao.setNtalao(cursor.getString(1));
                    talao.setQtrInicio(cursor.getString(2));
                    talao.setQtrLocal(cursor.getString(3));
                    talao.setQtr1Term(cursor.getString(4));
                    talao.setQtrFinal(cursor.getString(5));

                    talao.setKmInicio(cursor.getString(6));
                    talao.setKmLocal(cursor.getString(7));
                    talao.setKm1Term(cursor.getString(8));
                    talao.setKmFinal(cursor.getString(9));
                    talao.setEndereco(cursor.getString(10));
                    talao.setNatureza(cursor.getString(11));
                    talao.setData(cursor.getString(12));
                    talao.setResultado(cursor.getString(13));
                    talao.setObservacao(cursor.getString(14));
                    talao.setArquivado(cursor.getString(15));

                    listaDeTalao.add(talao);

                }
            }

        }


    void confirmDialog(String _id){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        if("gerarRelatorio".equals(_id)){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
            builder.setTitle("Gerar PDF da lista de talões");
            builder.setMessage("Será gerado um arquivo com o nome RSO_dataDeHoje na pasta Download.");
            builder.setPositiveButton("Gerar PDF", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                   createPDF();
                }
            });
        }
        if("delete_all".equals(_id)){
            builder.setTitle("Apagar todos os dados ");
            builder.setMessage("Tem certeza que você quer apagar todos os dados?");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(Ocorrencias_Inicial.this);
                    myDB.deleteAllData("Taloes");

                    //Atualizar a acticity
                    Intent intent = new Intent(Ocorrencias_Inicial.this, Ocorrencias_Inicial.class);
                    finish();
                    startActivity(intent);

                }
            });
        }
        if("arquivar_todos".equals(_id)){
            builder.setTitle("Arquivar todos as ocorrências");
            builder.setMessage("Tem certeza que você quer arquivar todos as ocorrências?");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    TalaoDAO talaoDAO = new TalaoDAO(getApplicationContext());
                    talaoDAO.arquivarTodos(listaDeTalao);


                    //Atualizar a acticity
                    Intent intent = new Intent(Ocorrencias_Inicial.this, Ocorrencias_Inicial.class);
                    finish();
                    startActivity(intent);

                }
            });
        }

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
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gerarRelatorio:
                //if(listaDeTalao.size()>0){
                    confirmDialog("gerarRelatorio");
                //}else {
                    //Toast.makeText(this, "Não há talões ativos", Toast.LENGTH_LONG).show();
                //}

                return true;
            case R.id.arquivar_todos:
                confirmDialog("arquivar_todos");
                return true;
            case R.id.delete_all:
                confirmDialog("delete_all");
                return true;
            default:
                 return super.onOptionsItemSelected(item);
        }

    }



    public void createPDF() {

        int top = 220;
        PdfDocument myPdfDocument = new PdfDocument();
        Paint myPaint = new Paint();
        Paint myPaint2 = new Paint();

        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(2100,2970,1).create();

       PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo);
        Canvas canvas = myPage1.getCanvas();
        canvas.drawBitmap(scaledCabecalho,150,-50,myPaint);
        canvas.drawBitmap(scaledImagem1,150,200,myPaint);
        canvas.drawBitmap(scaledImagem2,150,1353,myPaint);
        canvas.drawBitmap(scaledRodape,150,2450,myPaint);
        myPaint.setTextSize(45);

if(listaDeTalao.size()>0) {


    for (int i = 0; i < listaDeTalao.size(); i++) {

        myPaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(listaDeTalao.get(i).getKmInicio(), 355, top + 132, myPaint);
        canvas.drawText(listaDeTalao.get(i).getKmFinal(), 725, top + 132, myPaint);

        canvas.drawText(listaDeTalao.get(i).getQtrInicio().substring(0, 2), 325, top + 80, myPaint);
        canvas.drawText(listaDeTalao.get(i).getQtrInicio().substring(3, 5), 388, top + 80, myPaint);

        canvas.drawText(listaDeTalao.get(i).getQtrFinal().substring(0, 2), 690, top + 80, myPaint);
        canvas.drawText(listaDeTalao.get(i).getQtrFinal().substring(3, 5), 755, top + 80, myPaint);

        canvas.drawText(listaDeTalao.get(i).getEndereco(), 1310, top + 80, myPaint);

        canvas.drawText(listaDeTalao.get(i).getNatureza().substring(5), 1310, top + 132, myPaint);
        canvas.drawText(listaDeTalao.get(i).getNtalao(), 1860, top + 80, myPaint);
        canvas.drawText(listaDeTalao.get(i).getNatureza().substring(0, 3), 1860, top + 132, myPaint);


        top += 110;
    }
    myPaint.setTextSize(40);
    canvas.drawText(String.valueOf(listaDeTalao.size()), 1900, 2890, myPaint);

}


        myPdfDocument.finishPage(myPage1);



        PdfDocument.Page myPage2 = myPdfDocument.startPage(myPageInfo);
        String prelecao = "";
        MyDatabaseHelper myDb = new MyDatabaseHelper(getApplicationContext());
        Cursor cursor = myDb.readAllData("Relatorio","RSO");
        while (cursor.moveToNext()){
            prelecao = cursor.getString(1);
        }
        List<String> lista = new ArrayList<>();

        String prelecao2 = prelecao.replace("*"," *");
        String[] cabecalhoSeparado= prelecao2.split(" ");
        String valor = "";
        int topo = 290;



        Canvas canvas2 = myPage2.getCanvas();
        canvas2.drawBitmap(scaledRelatorio1,150,150,myPaint2);
        canvas2.drawBitmap(scaledRelatorio2,148,1207,myPaint2);
        canvas2.drawBitmap(scaledRelatorio3,150,2310,myPaint2);
        myPaint2.setTextSize(45);


        for (int i = 0;i<cabecalhoSeparado.length;i++){

           if(valor.length()<86 && valor.length()+cabecalhoSeparado[i].trim().length()<86) {

               if (cabecalhoSeparado[i].trim().length() > 3) {
                   if (cabecalhoSeparado[i].contains("*")) {
                    String  palavra = cabecalhoSeparado[i].replace("*", "");

                       if (!valor.isEmpty()) {
                           lista.add(valor);
                           valor = "";
                           valor+=palavra+" ";
                           continue;
                       }
                   }
               }

               valor += cabecalhoSeparado[i].trim() + " ";

           }else{
               lista.add(valor);
               valor = "";
               valor+=cabecalhoSeparado[i].trim()+" ";
           }

        }
        lista.add(valor);


            valor = "";
        //Adicionar resumos dos taloes
        /*for(int i =0; i<listaDeTalao.size();i++){
            if(valor.length()<95 && valor.length()+"T-".length()+listaDeTalao.get(i).getNtalao().length()+" ".length()<95){
                valor+="T-"+listaDeTalao.get(i).getNtalao()+" ";
            }else{
                lista.add(valor);
                valor = "";
                valor+="T-"+listaDeTalao.get(i).getNtalao()+" ";
            }
            if(valor.length()<92 && valor.length()+listaDeTalao.get(i).getResultado().length()+"; ".length()<92){
                valor+=listaDeTalao.get(i).getResultado()+"; ";
            }else{
                lista.add(valor);
                valor = "";
                valor+=listaDeTalao.get(i).getResultado()+"; ";
            }


        }
        lista.add(valor);*/

        for(int i=0;i<lista.size();i++){
            canvas2.drawText(lista.get(i),180,topo+22,myPaint2);
            topo+=74;
        }

        myPdfDocument.finishPage(myPage2);

        String filePath = Environment.getExternalStorageDirectory()+ "/Download/RSO_.pdf";
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }

        
        try {
            myPdfDocument.writeTo(new FileOutputStream(file));

        }catch (IOException e){
            e.printStackTrace();
        }

        myPdfDocument.close();
        Toast.makeText(this, "Arquivo salvo com sucesso!", Toast.LENGTH_SHORT).show();

    }

}//final da classe mainActivity