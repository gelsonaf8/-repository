package com.example.CadastroOcorrencias.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.defaultConfig.defaultConfig;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.helper.TalaoDAO;
import com.example.CadastroOcorrencias.model.Talao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Cadastrar_Ocorrencias extends AppCompatActivity {
    private TextInputEditText talao_input,qtr_inicio_input,txtQtrLocal,txtQtr1Term,qtr_final_input,km_inicio_input,txtKmLocal,txtKm1Term,km_final_input;
    private AutoCompleteTextView endereco_input;
    private TextInputEditText natureza_input,txtResultado;
    private  FloatingActionButton save_button;
    private EditText txtObservacao;
    private List<String> enderecoLista;
    private String[] ListaResultado;
    private String[] ListaNatureza;
    CheckBox checkBoxHabilitarCampos;
    private boolean isUpdate = false;
    Talao talao = new Talao();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_talao);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));
        Bundle dados = getIntent().getExtras();
        isUpdate = dados.getBoolean("Atualizar");



        talao_input = findViewById(R.id.talao_input);
        qtr_inicio_input = findViewById(R.id.qtr_inicio_input);
        txtQtrLocal = findViewById(R.id.txtQtrLocal);
        txtQtr1Term = findViewById(R.id.txtQtr1Term);
        qtr_final_input = findViewById(R.id.qtr_final_input);
        km_inicio_input = findViewById(R.id.km_inicio_input);
        txtKmLocal = findViewById(R.id.txtKmLocal);
        txtKm1Term = findViewById(R.id.txtKm1Term);
        km_final_input = findViewById(R.id.txtKMFinal);
        endereco_input = findViewById(R.id.endereco_input);
        natureza_input = findViewById(R.id.natureza_input);
        save_button = findViewById(R.id.save_button);
        txtResultado =findViewById(R.id.txtresultado);
        txtObservacao = findViewById(R.id.txtObs);

        ListaResultado = defaultConfig.listaResultado();
        ListaNatureza = defaultConfig.ListaNatureza();
        checkBoxHabilitarCampos = findViewById(R.id.checkBoxHabiitarCampos);
if(isUpdate){
    getAndSetIntentData();
    checkBoxHabilitarCampos.setVisibility(View.VISIBLE);
    desabilitarCamposPreenchidos();

}
checkBoxHabilitarCampos.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        HabilitarCamposInativos();
    }
});

       CarregarEnderecos();





        natureza_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                natureza_input.setInputType(InputType.TYPE_NULL);
                CriarMenu(v,ListaNatureza);
            }
        });

        natureza_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    natureza_input.setInputType(InputType.TYPE_NULL);

                    CriarMenu(v,ListaNatureza);
                }
            }
        });

        txtResultado.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    txtResultado.setInputType(InputType.TYPE_NULL);
                    CriarMenu(v,ListaResultado);
                }
            }
        });
        txtResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResultado.setInputType(InputType.TYPE_NULL);
                CriarMenu(v,ListaResultado);
            }
        });



        qtr_inicio_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Calendar calendar = Calendar.getInstance();
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minutos = calendar.get(Calendar.MINUTE);
                if(hasFocus){
                    TimePickerDialog timePickerDialog = new TimePickerDialog(Cadastrar_Ocorrencias.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            qtr_inicio_input.setText(String.format("%02d:%02d",hourOfDay,minute));
                        }
                    },hora,minutos,true);
                    timePickerDialog.show();

                }

            }
        });
       txtQtrLocal.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Calendar calendar = Calendar.getInstance();
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minutos = calendar.get(Calendar.MINUTE);
                if(hasFocus){
                    TimePickerDialog timePickerDialog = new TimePickerDialog(Cadastrar_Ocorrencias.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                           txtQtrLocal.setText(String.format("%02d:%02d",hourOfDay,minute));
                        }
                    },hora,minutos,true);
                    timePickerDialog.show();

                }

            }
        });

        txtQtr1Term.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Calendar calendar = Calendar.getInstance();
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minutos = calendar.get(Calendar.MINUTE);
                if(hasFocus){
                    TimePickerDialog timePickerDialog = new TimePickerDialog(Cadastrar_Ocorrencias.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            txtQtr1Term.setText(String.format("%02d:%02d",hourOfDay,minute));
                        }
                    },hora,minutos,true);
                    timePickerDialog.show();

                }

            }
        });


       qtr_final_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Calendar calendar = Calendar.getInstance();
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minutos = calendar.get(Calendar.MINUTE);
                if(hasFocus){

                    TimePickerDialog timePickerDialog = new TimePickerDialog(Cadastrar_Ocorrencias.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            qtr_final_input.setText(String.format("%02d:%02d",hourOfDay,minute));
                        }
                    },hora,minutos,true);
                    timePickerDialog.show();

                }

            }
        });
       ArrayAdapter<String> enderecoAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,enderecoLista);
        endereco_input.setAdapter(enderecoAdapter);




      save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(talao_input.getText().toString().isEmpty()){
                    //Toast.makeText(getApplicationContext(),"Adicione o numero do talão",Toast.LENGTH_SHORT).show();
                    Snackbar.make(v,"Adicione um número de talão",Snackbar.LENGTH_SHORT).show();
                }
                else{
                    Date dataAtual = new Date();
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(dataAtual);

                    //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();


                    talao.setNtalao(talao_input.getText().toString().trim());
                    talao.setData(data);
                    talao.setArquivado("Ativo");
                    talao.setKmInicio(km_inicio_input.getText().toString().trim());
                    talao.setKmLocal(txtKmLocal.getText().toString().trim());
                    talao.setKm1Term(txtKm1Term.getText().toString().trim());
                    talao.setKmFinal(km_final_input.getText().toString().trim());
                    talao.setQtrInicio(qtr_inicio_input.getText().toString().trim());
                    talao.setQtrLocal(txtQtrLocal.getText().toString().trim());
                    talao.setQtr1Term(txtQtr1Term.getText().toString().trim());
                    talao.setQtrFinal(qtr_final_input.getText().toString().trim());
                    talao.setEndereco(endereco_input.getText().toString().trim());
                    talao.setNatureza(natureza_input.getText().toString().trim());
                    talao.setObservacao(txtObservacao.getText().toString().trim());
                    talao.setResultado(txtResultado.getText().toString().trim());

                    TalaoDAO talaoDAO= new TalaoDAO(getApplicationContext());
                    if(isUpdate){
                        talaoDAO.updateData(talao,getApplicationContext());
                    }else{

                        talaoDAO.AdicionarTalao(talao,getApplicationContext());
                    }
                    Intent intent = new Intent(Cadastrar_Ocorrencias.this, Ocorrencias_Inicial.class);

                    startActivity(intent);
                    finish();


                }

                }

        });
    }//fim do metodo onCreate



    public  void CarregarEnderecos(){

        MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
        enderecoLista = new ArrayList<>();

     Cursor cursor = myDB.readAllData("Enderecos","E");

         while (cursor.moveToNext()){
             enderecoLista.add(cursor.getString(1));
         }

 }


    public void CriarMenu(final View v,final String[] lista){

        final TextInputEditText target  = (TextInputEditText) v;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Cadastrar_Ocorrencias.this);
        if(lista == ListaNatureza){
            alertDialogBuilder.setTitle("NATUREZA DA OCORRÊNCIA");
        }
        if(lista == ListaResultado){
            alertDialogBuilder.setTitle("RESULTADO DA OCORRÊNCIA");
        }

        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setSingleChoiceItems( lista, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                target.setText(lista[position]);
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }
    //Recuperando dados das intents e colocando nos inputs
    void getAndSetIntentData() {
        Bundle dados = getIntent().getExtras();
        talao = (Talao) dados.getSerializable("Talao");


        //Colocando dados nos Inputs

        talao_input.setText(talao.getNtalao());
        qtr_inicio_input.setText(talao.getQtrInicio());
        txtQtrLocal.setText(talao.getQtrLocal());
        txtQtr1Term.setText(talao.getQtr1Term());
        qtr_final_input.setText(talao.getQtrFinal());
        km_inicio_input.setText(talao.getKmInicio());
        txtKmLocal.setText(talao.getKmLocal());
        txtKm1Term.setText(talao.getKm1Term());
        km_final_input.setText(talao.getKmFinal());
        endereco_input.setText(talao.getEndereco());
        natureza_input.setText(talao.getNatureza());
        txtResultado.setText(talao.getResultado());
        txtObservacao.setText(talao.getObservacao());


    }

    public void desabilitarCamposPreenchidos(){

        if(!talao_input.getText().toString().isEmpty()){
            talao_input.setEnabled(false);
        }
        if(!qtr_inicio_input.getText().toString().isEmpty()){
            qtr_inicio_input.setEnabled(false);
        }
        if(!txtQtrLocal.getText().toString().isEmpty()){
            txtQtrLocal.setEnabled(false);
        }
        if(!txtQtr1Term.getText().toString().isEmpty()){
            txtQtr1Term.setEnabled(false);
        }
        if(!qtr_final_input.getText().toString().isEmpty()){
            qtr_final_input.setEnabled(false);
        }
        if(!km_inicio_input.getText().toString().isEmpty()){
            km_inicio_input.setEnabled(false);
        }
        if(!txtKmLocal.getText().toString().isEmpty()){
            txtKmLocal.setEnabled(false);
        }
        if(!txtKm1Term.getText().toString().isEmpty()){
            txtKm1Term.setEnabled(false);
        }
        if(!km_final_input.getText().toString().isEmpty()){
            km_final_input.setEnabled(false);
        }
        if(!endereco_input.getText().toString().isEmpty()){
            endereco_input.setEnabled(false);
        }
        if(!natureza_input.getText().toString().isEmpty()){
            natureza_input.setEnabled(false);
        }
        if(!txtResultado.getText().toString().isEmpty()){
            txtResultado.setEnabled(false);
        }
        if(!txtObservacao.getText().toString().isEmpty()){
            txtObservacao.setEnabled(false);
        }

    }

    public void HabilitarCamposInativos(){
        if(checkBoxHabilitarCampos.isChecked()){
            talao_input.setEnabled(true);
            qtr_inicio_input.setEnabled(true);
            txtQtrLocal.setEnabled(true);
            txtQtr1Term.setEnabled(true);
            qtr_final_input.setEnabled(true);
            km_inicio_input.setEnabled(true);
            txtKmLocal.setEnabled(true);
            txtKm1Term.setEnabled(true);
            km_final_input.setEnabled(true);
            endereco_input.setEnabled(true);
            natureza_input.setEnabled(true);
            txtResultado.setEnabled(true);
            txtObservacao.setEnabled(true);
        }else{
            desabilitarCamposPreenchidos();
        }
    }



    void confirmDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Apagar talão T-"+ talao.getNtalao()+"?");
        builder.setMessage("Tem certeza que você quer apagar o Talão?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Cadastrar_Ocorrencias.this);
                myDB.deleteOneRow(String.valueOf(talao.getId()),"Taloes");

                //Atualizar a acticity
                Intent intent = new Intent(Cadastrar_Ocorrencias.this, Ocorrencias_Inicial.class);
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
        if(isUpdate){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.update_menu,menu);

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.arquivarTalao:
                TalaoDAO talaoDAO = new TalaoDAO(getApplicationContext());
                talaoDAO.arquivarTalao(talao);
                Intent intent = new Intent(Cadastrar_Ocorrencias.this, Ocorrencias_Inicial.class);
                startActivity(intent);
                finish();

                return true;
            case R.id.apagarTalao:
                confirmDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



}//fim da classe main