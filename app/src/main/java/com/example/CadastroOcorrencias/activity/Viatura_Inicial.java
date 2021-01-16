package com.example.CadastroOcorrencias.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.adapter.AcessoriosAdapter;
import com.example.CadastroOcorrencias.adapter.MistoAdapter;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Acessorios;
import com.example.CadastroOcorrencias.model.Avaria;
import com.example.CadastroOcorrencias.model.Endereco;
import com.example.CadastroOcorrencias.model.Viatura;

import java.util.ArrayList;
import java.util.List;

public class Viatura_Inicial extends AppCompatActivity  {
RecyclerView recyclerAcessorios,recyclerFuncionamenos;
List<Acessorios> ListaAcessorios;
TextView btnExibirAcessorios,btnExibirFuncionamentos,btnExibirAvarias,btnExibirInformacoes,btnExibirOutros,txtModalidade,txtPrefixo,txtSetor,txtInformacoesKmInicio,txtInformacoesKmTermino,txtInformacoesKmRodados;
LinearLayout linearLayout5,linearLayout6;
HorizontalScrollView horizontalScrollView;
ConstraintLayout cLInformacoes,cLOutros;
Button av1,av2,av3,av4,av5,av6,av7,av8,av9,av10,av11,av12,av13,av14,av15,av16,av17,av18,av19,av20,av21,av22,av23,av24,av25,av26,av27,av28;
Button btnAssRes,btnAss1_4,btnAss1_2,btnAss3_4,btnAssCheio,btnPassRes,btnPass1_4,btnPass1_2,btnPass3_4,btnPassCheio;
Boolean isAcessoriosVisible = false,isFuncionamentoVisible = false,isAvariasVisible = false,isInformacoesVisible = false,isOutrosVisible = false;
Viatura viatura;
Avaria avaria;

List<Avaria> listaAvaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viatura);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));


       //Carrega widgets do app
        CarregarWidgets();
        CarregarInformacoesViatura();
        CarregarListaDeAcessorios();
        CarregarListaDeFuncionamento();
        CarregarAvarias();

        btnExibirInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInformacoesVisible){
                    cLInformacoes.setVisibility(View.GONE);
                    isInformacoesVisible=false;

                }else{
                    cLInformacoes.setVisibility(View.VISIBLE);
                    isInformacoesVisible=true;

                }

            }
        });
        /*cLInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Viatura_Inicial.this, Cadastrar_Viatura.class);
               intent.putExtra("Viatura",viatura);
                finish();
                startActivity(intent);
            }
        });*/

       btnExibirAcessorios.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(isAcessoriosVisible){
                   linearLayout5.setVisibility(View.GONE);
                   recyclerAcessorios.setVisibility(View.GONE);
                   isAcessoriosVisible=false;

               }else{
                   linearLayout5.setVisibility(View.VISIBLE);
                   recyclerAcessorios.setVisibility(View.VISIBLE);
                   isAcessoriosVisible=true;


               }

           }
       });
        btnExibirFuncionamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFuncionamentoVisible){
                    linearLayout6.setVisibility(View.GONE);
                    recyclerFuncionamenos.setVisibility(View.GONE);
                    isFuncionamentoVisible=false;

                }else{
                    linearLayout6.setVisibility(View.VISIBLE);
                    recyclerFuncionamenos.setVisibility(View.VISIBLE);
                    isFuncionamentoVisible=true;


                }

            }
        });
        btnExibirAvarias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAvariasVisible){
                    horizontalScrollView.setVisibility(View.GONE);
                    isAvariasVisible=false;

                }else{
                    horizontalScrollView.setVisibility(View.VISIBLE);
                    isAvariasVisible=true;

                }

            }
        });
        btnExibirOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOutrosVisible){
                    cLOutros.setVisibility(View.GONE);
                    isOutrosVisible=false;
                    isAvariasVisible=false;

                }else{
                    cLOutros.setVisibility(View.VISIBLE);
                    isOutrosVisible=true;


                }

            }
        });


    }


    public  void MostrarOpcoes(View v){
        final MyDatabaseHelper MyDB = new MyDatabaseHelper(getApplicationContext());
        final Button btn = (Button) v;
        final String[]listaDeAvarias = new String[]{"1 = Amassado","2 = Riscado","3 = Quebrado","4 = Manchado","5 = Descascado","6 - Outros","7 - Limpar"};


    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Viatura_Inicial.this);
    alertDialogBuilder.setTitle("AVARIAS");

   alertDialogBuilder.setSingleChoiceItems(listaDeAvarias, 0, new DialogInterface.OnClickListener() {
       @Override
       public void onClick(DialogInterface dialog, int position) {
          if(btn.getText().toString().isEmpty()){
              btn.setText(listaDeAvarias[position].trim().substring(0,1));
          }else{
              String avarias = btn.getText().toString().trim() +"/"+listaDeAvarias[position].trim().substring(0,1);
             btn.setText(avarias);
          }
          if(position==6){
             btn.setText("");
          }
           String id = btn.getTag().toString().trim();
           MyDB.atualizarAvarias(btn.getText().toString().trim(),id);
           dialog.dismiss();



       }
   });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

}

    public void CarregarWidgets(){
    recyclerAcessorios = findViewById(R.id.recyclerAcessorios);
    recyclerFuncionamenos = findViewById(R.id.recyclerFuncionamento);

            btnExibirAcessorios =findViewById(R.id.btnExibirAcessorios);
            btnExibirFuncionamentos = findViewById(R.id.btnExibirFuncionamento);
            btnExibirAvarias = findViewById(R.id.btnExibirAvarias);
            btnExibirInformacoes = findViewById(R.id.btnExibirInformacoes);
            btnExibirOutros = findViewById(R.id.btnExibirOutros);
            txtModalidade = findViewById(R.id.txtModalidade);
            txtPrefixo = findViewById(R.id.txtPrefixo);
            txtSetor = findViewById(R.id.txtSetor);
            txtInformacoesKmInicio = findViewById(R.id.txtInformacoesKmInicio);
            txtInformacoesKmTermino = findViewById(R.id.txtInformacoesKmTermino);
            txtInformacoesKmRodados = findViewById(R.id.txtInformacoesKmRodados);

            linearLayout5 = findViewById(R.id.linearLayout5);
            linearLayout6 =findViewById(R.id.linearLayout6);

            horizontalScrollView = findViewById(R.id.horizontalScrollView);
            cLInformacoes = findViewById(R.id.ConstrainInformacoes);
            cLOutros = findViewById(R.id.ConstrainsOutros);

            av1 = findViewById(R.id.av1);
            av2 = findViewById(R.id.av2);
            av3 = findViewById(R.id.av3);
            av4 = findViewById(R.id.av4);
            av5 = findViewById(R.id.av5);
            av6 = findViewById(R.id.av6);
            av7 = findViewById(R.id.av7);
            av8 = findViewById(R.id.av8);

            av9 = findViewById(R.id.av9);
            av10 = findViewById(R.id.av10);
            av11 = findViewById(R.id.av11);
            av12 = findViewById(R.id.av12);

            av13 = findViewById(R.id.av13);
            av14= findViewById(R.id.av14);
            av15= findViewById(R.id.av15);
            av16 = findViewById(R.id.av16);
            av17 = findViewById(R.id.av17);
            av18 = findViewById(R.id.av18);
            av19 = findViewById(R.id.av19);
            av20= findViewById(R.id.av20);
            av21 = findViewById(R.id.av21);
            av22 = findViewById(R.id.av22);
            av23 = findViewById(R.id.av23);
            av24 = findViewById(R.id.av24);
            av25 = findViewById(R.id.av25);
            av26 = findViewById(R.id.av26);
            av27 = findViewById(R.id.av27);
            av28 = findViewById(R.id.av28);

            btnAssRes = findViewById(R.id.btnAssRes);
            btnAss1_4 = findViewById(R.id.btnAss1_4);
            btnAss1_2 = findViewById(R.id.btnAss1_2);
            btnAss3_4 = findViewById(R.id.btnAss3_4);
            btnAssCheio = findViewById(R.id.btnAssCheio);

            btnPassRes = findViewById(R.id.btnPassRes);
            btnPass1_4 = findViewById(R.id.btnPass1_4);
            btnPass1_2 = findViewById(R.id.btnPass1_2);
            btnPass3_4 = findViewById(R.id.btnPass3_4);
            btnPassCheio = findViewById(R.id.btnPassCheio);


    }
    public void CarregarListaDeAcessorios(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());

        Cursor cursor =myDB.readAllData("Acessorios","AC");
        ListaAcessorios = new ArrayList<>();

            while (cursor.moveToNext()){
                Acessorios acessorios = new Acessorios();

                acessorios.setId(cursor.getInt(0));
                acessorios.setDescricao(cursor.getString(1));
               acessorios.setSim(cursor.getString(2));
               acessorios.setCategoria(cursor.getString(3));

                ListaAcessorios.add(acessorios);
            }

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            AcessoriosAdapter acessoriosAdapter = new AcessoriosAdapter(ListaAcessorios,getApplicationContext(), Viatura_Inicial.this);
            recyclerAcessorios.setLayoutManager(layoutManager);
            recyclerAcessorios.setHasFixedSize(true);
            recyclerAcessorios.setAdapter(acessoriosAdapter);

        }
    public void CarregarListaDeFuncionamento(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());

        Cursor cursor =myDB.readAllData("Acessorios","FC");
        ListaAcessorios = new ArrayList<>();

        while (cursor.moveToNext()){
            Acessorios acessorios = new Acessorios();

            acessorios.setId(cursor.getInt(0));
            acessorios.setDescricao(cursor.getString(1));
            acessorios.setSim(cursor.getString(2));
            acessorios.setCategoria(cursor.getString(3));

            ListaAcessorios.add(acessorios);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        AcessoriosAdapter acessoriosAdapter = new AcessoriosAdapter(ListaAcessorios,getApplicationContext(), Viatura_Inicial.this);
        recyclerFuncionamenos.setLayoutManager(layoutManager);
        recyclerFuncionamenos.setHasFixedSize(true);
        recyclerFuncionamenos.setAdapter(acessoriosAdapter);

    }
    public void CarregarInformacoesViatura(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
        viatura= new Viatura();
        int inicio,termino = 0,rodado=0;
        Cursor cursor =myDB.readAllData("Viatura","VTR");

        while (cursor.moveToNext()){
           viatura.set_id(cursor.getInt(0));
           viatura.setPrefixo(cursor.getString(1));
           viatura.setModalidade(cursor.getString(2));
           viatura.setSetor(cursor.getString(3));
           viatura.setKmInicio(cursor.getString(4));
           viatura.setKmTermino(cursor.getString(5));
           viatura.setCombustivelAssumido(cursor.getString(6));
           viatura.setCombustivelPassado(cursor.getString(7));
           viatura.setAcabamentoInterno(cursor.getString(8));
           viatura.setObs(cursor.getString(9));
        }
        txtPrefixo.setText(viatura.getPrefixo());
        txtModalidade.setText(viatura.getModalidade());
        txtSetor.setText(viatura.getSetor());
        txtInformacoesKmInicio.setText(viatura.getKmInicio());
        txtInformacoesKmTermino.setText(viatura.getKmTermino());

        try{

            inicio = Integer.parseInt(viatura.getKmInicio().trim());
            termino = Integer.parseInt(viatura.getKmTermino().trim());

            rodado = termino-inicio;
            txtInformacoesKmRodados.setText(String.valueOf(rodado));
        }catch (NumberFormatException e){
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }

        switch (viatura.getCombustivelAssumido()){
            case "1":
                btnAssRes.setText("X");
                break;
            case "2":
                btnAss1_4.setText("X");
                break;
            case "3":
                btnAss1_2.setText("X");
                break;
            case "4":
                btnAss3_4.setText("X");
                break;
            case "5":
                btnAssCheio.setText("X");
                break;

        }
        switch (viatura.getCombustivelPassado()){
            case "1":
                btnPassRes.setText("X");
                break;
            case "2":
                btnPass1_4.setText("X");
                break;
            case "3":
                btnPass1_2.setText("X");
                break;
            case "4":
                btnPass3_4.setText("X");
                break;
            case "5":
                btnPassCheio.setText("X");
                break;

        }





    }
    public void CarregarAvarias(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());
       listaAvaria = new ArrayList<>();
        Cursor cursor =myDB.readAllData("Avarias","T");

        while (cursor.moveToNext()){
             avaria = new Avaria();
            avaria.set_id(cursor.getInt(0));
            avaria.setDescricao(cursor.getString(1));
           listaAvaria.add(avaria);
        }


        av1.setText(listaAvaria.get(0).getDescricao());
        av2.setText(listaAvaria.get(1).getDescricao());
        av3.setText(listaAvaria.get(2).getDescricao());
        av4.setText(listaAvaria.get(3).getDescricao());
        av5.setText(listaAvaria.get(4).getDescricao());
        av6.setText(listaAvaria.get(5).getDescricao());
        av7.setText(listaAvaria.get(6).getDescricao());
        av8.setText(listaAvaria.get(7).getDescricao());
        av9.setText(listaAvaria.get(8).getDescricao());
        av10.setText(listaAvaria.get(9).getDescricao());
        av11.setText(listaAvaria.get(10).getDescricao());
        av12.setText(listaAvaria.get(11).getDescricao());
        av13.setText(listaAvaria.get(12).getDescricao());
        av14.setText(listaAvaria.get(13).getDescricao());
        av15.setText(listaAvaria.get(14).getDescricao());
        av16.setText(listaAvaria.get(15).getDescricao());
        av17.setText(listaAvaria.get(16).getDescricao());
        av18.setText(listaAvaria.get(17).getDescricao());
        av19.setText(listaAvaria.get(18).getDescricao());
        av20.setText(listaAvaria.get(19).getDescricao());
        av21.setText(listaAvaria.get(20).getDescricao());
        av22.setText(listaAvaria.get(21).getDescricao());
        av23.setText(listaAvaria.get(22).getDescricao());
        av24.setText(listaAvaria.get(23).getDescricao());
        av25.setText(listaAvaria.get(24).getDescricao());
        av26.setText(listaAvaria.get(25).getDescricao());
        av27.setText(listaAvaria.get(26).getDescricao());
        av28.setText(listaAvaria.get(27).getDescricao());



    }

    public void MarcarCombustivel(View v){
        Button btn = (Button) v;
        MyDatabaseHelper myDB = new MyDatabaseHelper(getApplicationContext());

        switch (btn.getId()){
            case R.id.btnAssRes:
                btnAssRes.setText("X");
                btnAss1_4.setText("");
                btnAss1_2.setText("");
                btnAss3_4.setText("");
                btnAssCheio.setText("");
                viatura.setCombustivelAssumido("1");
                break;
            case R.id.btnAss1_4:
                btnAssRes.setText("");
                btnAss1_4.setText("X");
                btnAss1_2.setText("");
                btnAss3_4.setText("");
                btnAssCheio.setText("");
                viatura.setCombustivelAssumido("2");
                break;
            case R.id.btnAss1_2:
                btnAssRes.setText("");
                btnAss1_4.setText("");
                btnAss1_2.setText("X");
                btnAss3_4.setText("");
                btnAssCheio.setText("");
                viatura.setCombustivelAssumido("3");
                break;
            case R.id.btnAss3_4:
                btnAssRes.setText("");
                btnAss1_4.setText("");
                btnAss1_2.setText("");
                btnAss3_4.setText("X");
                btnAssCheio.setText("");
                viatura.setCombustivelAssumido("4");
                break;
            case R.id.btnAssCheio:
                btnAssRes.setText("");
                btnAss1_4.setText("");
                btnAss1_2.setText("");
                btnAss3_4.setText("");
                btnAssCheio.setText("X");
                viatura.setCombustivelAssumido("5");
                break;

            case R.id.btnPassRes:
                btnPassRes.setText("X");
                btnPass1_4.setText("");
                btnPass1_2.setText("");
                btnPass3_4.setText("");
                btnPassCheio.setText("");
                viatura.setCombustivelPassado("1");
                break;
            case R.id.btnPass1_4:
                btnPassRes.setText("");
                btnPass1_4.setText("X");
                btnPass1_2.setText("");
                btnPass3_4.setText("");
                btnPassCheio.setText("");
                viatura.setCombustivelPassado("2");
                break;
            case R.id.btnPass1_2:
                btnPassRes.setText("");
                btnPass1_4.setText("");
                btnPass1_2.setText("X");
                btnPass3_4.setText("");
                btnPassCheio.setText("");
                viatura.setCombustivelPassado("3");
                break;
            case R.id.btnPass3_4:
                btnPassRes.setText("");
                btnPass1_4.setText("");
                btnPass1_2.setText("");
                btnPass3_4.setText("X");
                btnPassCheio.setText("");
                viatura.setCombustivelPassado("4");
                break;
            case R.id.btnPassCheio:
                btnPassRes.setText("");
                btnPass1_4.setText("");
                btnPass1_2.setText("");
                btnPass3_4.setText("");
                btnPassCheio.setText("X");
                viatura.setCombustivelPassado("5");
                break;
            default:
                Toast.makeText(this, "Não foi possível salvar os dados", Toast.LENGTH_SHORT).show();

        }

        myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));

    }


}