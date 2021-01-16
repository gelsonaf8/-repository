package com.example.CadastroOcorrencias.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.adapter.MistoAdapter;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Endereco;
import com.example.CadastroOcorrencias.model.Natureza;
import com.example.CadastroOcorrencias.model.Resultado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class Cadastrar_Enderecos extends AppCompatActivity {

    private String endereco;
    private TextInputEditText enderecoInput;
    private FloatingActionButton addEndereco;
    private ImageView imgSemDados;
    TextView txtSemDados;
    RecyclerView recyclerView;
    List<Endereco> ListaEndereco;
    List<Natureza> ListaNatureza;
    List<Resultado> ListaResultado;
    RadioButton checkEndereco,checkNatureza,checkResultado;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_endereco);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));

        addEndereco = findViewById(R.id.addEndereco);
        recyclerView = findViewById(R.id.RecyclerViewMisto);
        imgSemDados = findViewById(R.id.imgSemDados);
        txtSemDados = findViewById(R.id.txtSemDados);
        checkEndereco = findViewById(R.id.check_enderecos);
        checkNatureza = findViewById(R.id.check_natureza);
        checkResultado = findViewById(R.id.check_resultado);
        radioGroup = findViewById(R.id.radioGroup);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        final MistoAdapter mistoAdapter = new MistoAdapter(ListaEndereco,getApplicationContext(), Cadastrar_Enderecos.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mistoAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        CarregarEnderecos("Enderecos","E");

     radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(RadioGroup group, int checkedId) {
             switch (group.getCheckedRadioButtonId()){
                 case R.id.check_enderecos:
                     CarregarEnderecos("Enderecos","E");


                     break;
                 case R.id.check_natureza:
                     CarregarEnderecos("ListaNatureza","N");
                     break;
                 case R.id.check_resultado:
                     CarregarEnderecos("ListaResultado","R");
                     break;
                 default:
             }
         }
     });

        addEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirInput();
            }
        });

    }

    void abrirInput(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        if(checkEndereco.isChecked()){
            builder.setTitle("ADICIONAR ENDEREÇO");
            builder.setMessage("Digite o nome da rua, avenida, etc");
        }
        if(checkNatureza.isChecked()){
            builder.setTitle("ADICIONAR NATUREZA");
            builder.setMessage("Digite um natureza da ocorrência");
        }
        if(checkResultado.isChecked()){
            builder.setTitle("ADICIONAR TIPO RESULTADO");
            builder.setMessage("Digite um tipo de resultado");
        }
        enderecoInput = new TextInputEditText(this);


        builder.setView(enderecoInput);

        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
                if(!enderecoInput.getText().toString().isEmpty()){
                    endereco = enderecoInput.getText().toString().trim();
                    if(checkEndereco.isChecked()){
                        myDB.adicionarMisto(endereco,"Enderecos");
                        CarregarEnderecos("Enderecos","E");
                    }
                    if(checkNatureza.isChecked()){
                        myDB.adicionarMisto(endereco,"ListaNatureza");
                        CarregarEnderecos("ListaNatureza","N");
                    }
                    if(checkResultado.isChecked()){
                        myDB.adicionarMisto(endereco,"ListaResultado");
                        CarregarEnderecos("ListaResultado","R");

                    }

                }


            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();



    }

    public void CarregarEnderecos(String tName,String s){
        MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());

       Cursor cursor =myDB.readAllData(tName,s);
        ListaEndereco = new ArrayList<>();

        if(cursor.getCount() == 0){
            imgSemDados.setVisibility(View.VISIBLE);
            txtSemDados.setVisibility(View.VISIBLE);


        }else{
            imgSemDados.setVisibility(View.GONE);
            txtSemDados.setVisibility(View.GONE);
            while (cursor.moveToNext()){
                Endereco endereco = new Endereco();

                endereco.setId(String.valueOf(cursor.getInt(0)));
                endereco.setNome(cursor.getString(1));

                ListaEndereco.add(endereco);
            }

        }

    }


}