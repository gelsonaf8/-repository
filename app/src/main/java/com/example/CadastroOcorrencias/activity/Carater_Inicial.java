package com.example.CadastroOcorrencias.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.adapter.CaraterAdapter;
import com.example.CadastroOcorrencias.adapter.CustomAdapter;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Abordado;
import com.example.CadastroOcorrencias.model.Carater;
import com.example.CadastroOcorrencias.model.Talao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Carater_Inicial extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Carater> ListaCarater;
    CaraterAdapter caraterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carater__inicial);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));
        recyclerView = findViewById(R.id.recyclerCarater);
        ListaCarater = new ArrayList<>();
        CarregarDados();


       caraterAdapter = new CaraterAdapter(ListaCarater,getApplicationContext(),Carater_Inicial.this);
        recyclerView.setAdapter(caraterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Carater_Inicial.this));
        recyclerView.setHasFixedSize(true);



    }





    public void CarregarDados(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
        Cursor cursor = myDB.readAllData("Carater","RSO");




        while(cursor.moveToNext()){
            Carater carater = new Carater();
            carater.setId(cursor.getInt(0));
            carater.setNumeros(cursor.getString(1));
            carater.setLetras(cursor.getString(2));
            carater.setModelo(cursor.getString(3));
            carater.setCor(cursor.getString(4));
            carater.setAno(cursor.getString(5));
            carater.setArea(cursor.getString(6));
            carater.setNatureza(cursor.getString(7));

            ListaCarater.add(carater);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_gerar_pdf,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.gerarPdfCarater:

                CriarPDFCarater();


                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void CriarPDFCarater(){
        //CRA PDF LISTA DE CARATER

        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH)+1;
        int ano = calendar.get(Calendar.YEAR);
        int hora2 = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos2 = calendar.get(Calendar.MINUTE);
        String data= String.format("%02d%02d%02d",dia,mes,ano);




        PdfDocument myPdfDocument = new PdfDocument();
            Paint myPaint = new Paint();

            PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1100,1500,1).create();
            PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo);
            Canvas canvas = myPage1.getCanvas();

            myPaint.setTextSize(45);
            myPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("LISTA DE CARATER "+dia+"/"+mes+"/"+ano,550,100,myPaint);
            myPaint.setStrokeWidth(2);
            int posisitionY = 120;
            for(int i=0;i<17;i++){
                canvas.drawLine(50,posisitionY,1050,posisitionY,myPaint);
                posisitionY+=80;
            }
        myPaint.setTextAlign(Paint.Align.CENTER);
        myPaint.setTextSize(35);
            canvas.drawText("Numero",125,190,myPaint);
        canvas.drawText("Letras",260,190,myPaint);
        canvas.drawText("Modelo",515,190,myPaint);

        canvas.drawText("Cor",760,190,myPaint);
        canvas.drawText("Ano",850,190,myPaint);
        canvas.drawText("Area",930,190,myPaint);
        canvas.drawText("R/F",1010,190,myPaint);
            canvas.drawLine(50,120,50,1400,myPaint);
        canvas.drawLine(200,120,200,1400,myPaint);
        canvas.drawLine(320,120,320,1400,myPaint);
        canvas.drawLine(710,120,710,1400,myPaint);
        canvas.drawLine(810,120,810,1400,myPaint);
        canvas.drawLine(890,120,890,1400,myPaint);

        canvas.drawLine(970,120,970,1400,myPaint);
        canvas.drawLine(1050,120,1050,1400,myPaint);

        myPaint.setTextSize(50);
        posisitionY = 270;
        for(Carater carater:ListaCarater){
            canvas.drawText(carater.getNumeros(),125,posisitionY,myPaint);
            canvas.drawText(carater.getLetras(),260,posisitionY,myPaint);
            canvas.drawText(carater.getModelo(),515,posisitionY,myPaint);
            canvas.drawText(carater.getCor(),760,posisitionY,myPaint);
            canvas.drawText(carater.getAno(),850,posisitionY,myPaint);
            canvas.drawText(carater.getArea(),930,posisitionY,myPaint);
            canvas.drawText(carater.getNatureza(),1010,posisitionY,myPaint);
            posisitionY+=80;

        }




            //Linhas auxiliares
             /*   int x = 50;
                int y = 20;
                myPaint.setStrokeWidth(1);

                myPaint.setTextSize(10);
                myPaint.setTextAlign(Paint.Align.CENTER);
                for(int i = 0;i<96;i++){
                    if(i%2==0){
                        canvas.drawText(String.valueOf(x),x,1400,myPaint);
                        canvas.drawText(String.valueOf(x),x,20,myPaint);
                        canvas.drawLine(x,20,x,1390,myPaint);
                    }else{
                        canvas.drawText(String.valueOf(x),x,1420,myPaint);
                        canvas.drawText(String.valueOf(x),x,10,myPaint);
                        canvas.drawLine(x,10,x,1390,myPaint);
                    }


                    x+=10;
                }

                //linhas horizontais
                for(int i = 0;i<138;i++){
                    canvas.drawText(String.valueOf(y),1020,y+3,myPaint);
                    canvas.drawText(String.valueOf(y),30,y+3,myPaint);
                    canvas.drawLine(40,y,1010,y,myPaint);
                    y+=10;
                }*/

            myPdfDocument.finishPage(myPage1);

            //String data= String.format("%02d%02d%02d_%02d%02d",dia2,mes2,ano2,hora2,minutos2);

            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/ListaCarater"+data+".pdf";
            File file = new File(filePath);

            try {
                myPdfDocument.writeTo(new FileOutputStream(file));
            }catch (IOException e){
                e.printStackTrace();
            }


            myPdfDocument.close();
            Toast.makeText(Carater_Inicial.this, "Documento criado com sucesso", Toast.LENGTH_SHORT).show();
        }

    }


