package com.example.CadastroOcorrencias.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.AbordadoDAO;
import com.example.CadastroOcorrencias.model.Abordado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class Cadastrar_Pessoas_Interresse extends AppCompatActivity {
    ImageView foto,btnCameraTatto1,btnCameraTatto2,btnCameraTatto3,btnCameraTatto4,btnCameraTatto5,btnCameraTatto6;
    LinearLayout linearLayout;
FloatingActionButton floatAddAbordados,btnTirarFoto;
TextInputEditText txtNomeAbordado,txtRgAbordado,txtArtigosCriminais,txtTatuagem,txtVulgo;
Button txtData,txtHoras;
AutoCompleteTextView txtEnderecoAbordagem;
EditText txtObservacaoAbodados;
byte[] fotoConvertida,tatoo1,tatoo2,tatoo3,tatoo4,tatoo5,tatoo6;


Calendar calendar = Calendar.getInstance();
    int dia = calendar.get(Calendar.DAY_OF_MONTH);
    int mes = calendar.get(Calendar.MONTH);
    int ano = calendar.get(Calendar.YEAR);
    int hora = calendar.get(Calendar.HOUR_OF_DAY);
    int minutos = calendar.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_abordados);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},0);
        }

        txtNomeAbordado = findViewById(R.id.txtNomeAbordado);
        txtRgAbordado = findViewById(R.id.txtRgAbordado);
        txtEnderecoAbordagem = findViewById(R.id.txtEnderecoAbordagem);
        txtVulgo  =findViewById(R.id.txtVulgo);
        txtData = findViewById(R.id.txtData);
        txtHoras = findViewById(R.id.txtHoras);
        txtArtigosCriminais = findViewById(R.id.txtArtigosCriminais);
        txtTatuagem = findViewById(R.id.txtTatuagem);
        txtObservacaoAbodados = findViewById(R.id.txtObservacaoAbordados);
        floatAddAbordados = findViewById(R.id.floatAddAbordados);
        txtData.setText(String.format("%02d/%02d/%02d",dia,mes,ano));
        txtHoras.setText(String.format("%02d:%02d",hora,minutos));
        btnTirarFoto = findViewById(R.id.btnTirarFoto);
        foto = findViewById(R.id.Foto);
        btnCameraTatto1 = findViewById(R.id.btnCameraTatto1);
        btnCameraTatto2 = findViewById(R.id.btnCameraTatto2);
        btnCameraTatto3 = findViewById(R.id.btnCameraTatto3);
        linearLayout = findViewById(R.id.linearLayout2);
        btnCameraTatto4 = findViewById(R.id.btnCameraTatto4);
        btnCameraTatto5 = findViewById(R.id.btnCameraTatto5);
        btnCameraTatto6 = findViewById(R.id.btnCameraTatto6);

        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TirarFoto(0);
            }
        });
        btnCameraTatto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TirarFoto(1);
            }
        });
        btnCameraTatto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TirarFoto(2);
            }
        });
        btnCameraTatto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TirarFoto(3);
            }
        });
        btnCameraTatto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TirarFoto(4);
            }
        });
        btnCameraTatto5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TirarFoto(5);
            }
        });
        btnCameraTatto6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TirarFoto(6);
            }
        });

           txtData.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   DatePickerDialog datePickerDialog = new DatePickerDialog(Cadastrar_Pessoas_Interresse.this, new DatePickerDialog.OnDateSetListener() {
                       @Override
                       public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                            txtData.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                       }
                   },dia,mes,ano);
                   datePickerDialog.show();
               }
           });
        txtHoras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(Cadastrar_Pessoas_Interresse.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            txtHoras.setText(String.format("%02d:%02d",hourOfDay,minute));
                        }
                    },hora,minutos,true);
                    timePickerDialog.show();
            }
        });

        floatAddAbordados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Abordado abordado = new Abordado();
                abordado.setFoto(fotoConvertida);
                abordado.setNome(txtNomeAbordado.getText().toString().trim());
                abordado.setRg(txtRgAbordado.getText().toString().trim());
                abordado.setEnderecoAbordagem(txtEnderecoAbordagem.getText().toString().trim());
                abordado.setAtrAbordagem(txtHoras.getText().toString());
                abordado.setDataAbordagem(txtData.getText().toString());
                abordado.setVulgo(txtVulgo.getText().toString());
                abordado.setArtigos(txtArtigosCriminais.getText().toString().trim());
                abordado.setTatuagem(txtTatuagem.getText().toString().trim());
                abordado.setObservacao(txtObservacaoAbodados.getText().toString().trim());
                abordado.setTatoo1(tatoo1);
                abordado.setTatoo2(tatoo2);
                abordado.setTatoo3(tatoo3);
                abordado.setTatoo4(tatoo4);
                abordado.setTatoo5(tatoo5);
                abordado.setTatoo6(tatoo6);
                AbordadoDAO abordadoDAO = new AbordadoDAO(getApplicationContext());

                if(abordadoDAO.salvarAbordado(abordado)){
                    Toast.makeText(getApplicationContext(),"Adicionado com sucesso!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Cadastrar_Pessoas_Interresse.this, Pessoas_Interresse_Inicial.class);
                            finish();
                            startActivity(intent);

                }

            }
        });



    }//fim onCreate


    public void TirarFoto(int requestCode){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 0 && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            foto.setImageBitmap(imagem);

            //comverter imagem para tipo Byte para adicionar no banco de dados
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.PNG,100,stream);
           fotoConvertida= stream.toByteArray();

        }
        if(requestCode == 1 && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            btnCameraTatto1.setImageBitmap(imagem);
            btnCameraTatto2.setVisibility(View.VISIBLE);

            //comverter imagem para tipo Byte para adicionar no banco de dados
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.PNG,100,stream);
            tatoo1= stream.toByteArray();

        }
        if(requestCode == 2 && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            btnCameraTatto2.setImageBitmap(imagem);
            btnCameraTatto3.setVisibility(View.VISIBLE);

            //comverter imagem para tipo Byte para adicionar no banco de dados
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.PNG,100,stream);
            tatoo2= stream.toByteArray();

        }
        if(requestCode == 3 && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            btnCameraTatto3.setImageBitmap(imagem);
            linearLayout.setVisibility(View.VISIBLE);
            btnCameraTatto4.setVisibility(View.VISIBLE);

            //comverter imagem para tipo Byte para adicionar no banco de dados
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.PNG,100,stream);
            tatoo3= stream.toByteArray();

        }
        if(requestCode == 4 && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            btnCameraTatto4.setImageBitmap(imagem);
            btnCameraTatto5.setVisibility(View.VISIBLE);
            //comverter imagem para tipo Byte para adicionar no banco de dados
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.PNG,100,stream);
            tatoo4= stream.toByteArray();

        }
        if(requestCode == 5 && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            btnCameraTatto5.setImageBitmap(imagem);
            btnCameraTatto6.setVisibility(View.VISIBLE);
            //comverter imagem para tipo Byte para adicionar no banco de dados
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.PNG,100,stream);
            tatoo5= stream.toByteArray();

        }
        if(requestCode == 6 && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            btnCameraTatto6.setImageBitmap(imagem);
            //comverter imagem para tipo Byte para adicionar no banco de dados
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.PNG,100,stream);
            tatoo6= stream.toByteArray();

        }

        super.onActivityResult(requestCode, resultCode, data);
    }




}//fim Classe Main