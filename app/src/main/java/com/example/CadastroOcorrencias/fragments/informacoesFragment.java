package com.example.CadastroOcorrencias.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Viatura;


public class informacoesFragment extends Fragment {
    TextView txtModalidade,txtPrefixo,txtSetor,txtInformacoesKmInicio,txtInformacoesKmTermino,txtInformacoesKmRodados;
    Viatura viatura;
    EditText txtInformacoesVtrObs;
    Button btnAssRes,btnAss1_4,btnAss1_2,btnAss3_4,btnAssCheio,btnPassRes,btnPass1_4,btnPass1_2,btnPass3_4,btnPassCheio;

    ConstraintLayout clInformacoes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_informacoes_viatura,container,false);
        txtModalidade = view.findViewById(R.id.txtModalidade);
        txtPrefixo = view.findViewById(R.id.txtPrefixo);
        txtSetor = view.findViewById(R.id.txtSetor);
        txtInformacoesKmInicio = view.findViewById(R.id.txtInformacoesKmInicio);
        txtInformacoesKmTermino = view.findViewById(R.id.txtInformacoesKmTermino);
        txtInformacoesKmRodados = view.findViewById(R.id.txtInformacoesKmRodados);
        txtInformacoesVtrObs = view.findViewById(R.id.txtInformacoesVtrObs);
        btnAssRes = view.findViewById(R.id.btnAssRes);
        btnAss1_4 = view.findViewById(R.id.btnAss1_4);
        btnAss1_2 = view.findViewById(R.id.btnAss1_2);
        btnAss3_4 = view.findViewById(R.id.btnAss3_4);
        btnAssCheio = view.findViewById(R.id.btnAssCheio);
        btnPassRes = view.findViewById(R.id.btnPassRes);
        btnPass1_4 = view.findViewById(R.id.btnPass1_4);
        btnPass1_2 = view.findViewById(R.id.btnPass1_2);
        btnPass3_4 = view.findViewById(R.id.btnPass3_4);
        btnPassCheio = view.findViewById(R.id.btnPassCheio);
        final MyDatabaseHelper myDB = new MyDatabaseHelper(getContext());

        clInformacoes = view.findViewById(R.id.cLInformacoes);
        CarregarInformacoesViatura();

        clInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new cadastrarViaturaFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Viatura",viatura);
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, myFragment).addToBackStack(null).commit();
            }
        });
        btnAssRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAssRes.setText("X");
                btnAss1_4.setText("");
                btnAss1_2.setText("");
                btnAss3_4.setText("");
                btnAssCheio.setText("");
                viatura.setCombustivelAssumido("1");
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });
        btnAss1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnAssRes.setText("");
                btnAss1_4.setText("X");
                btnAss1_2.setText("");
                btnAss3_4.setText("");
                btnAssCheio.setText("");
                viatura.setCombustivelAssumido("2");
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });
        btnAss1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAssRes.setText("");
                btnAss1_4.setText("");
                btnAss1_2.setText("X");
                btnAss3_4.setText("");
                btnAssCheio.setText("");
                viatura.setCombustivelAssumido("3");
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });
        btnAss3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAssRes.setText("");
                btnAss1_4.setText("");
                btnAss1_2.setText("");
                btnAss3_4.setText("X");
                btnAssCheio.setText("");
                viatura.setCombustivelAssumido("4");
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });
        btnAssCheio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAssRes.setText("");
                btnAss1_4.setText("");
                btnAss1_2.setText("");
                btnAss3_4.setText("");
                btnAssCheio.setText("X");
                viatura.setCombustivelAssumido("5");
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });
        btnPassRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPassRes.setText("X");
                btnPass1_4.setText("");
                btnPass1_2.setText("");
                btnPass3_4.setText("");
                btnPassCheio.setText("");
                viatura.setCombustivelPassado("1");
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });
        btnPass1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPassRes.setText("");
                btnPass1_4.setText("X");
                btnPass1_2.setText("");
                btnPass3_4.setText("");
                btnPassCheio.setText("");
                viatura.setCombustivelPassado("2");
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });
        btnPass1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPassRes.setText("");
                btnPass1_4.setText("");
                btnPass1_2.setText("X");
                btnPass3_4.setText("");
                btnPassCheio.setText("");
                viatura.setCombustivelPassado("3");
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });
        btnPass3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPassRes.setText("");
                btnPass1_4.setText("");
                btnPass1_2.setText("");
                btnPass3_4.setText("X");
                btnPassCheio.setText("");
                viatura.setCombustivelPassado("4");
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });
        btnPassCheio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPassRes.setText("");
                btnPass1_4.setText("");
                btnPass1_2.setText("");
                btnPass3_4.setText("");
                btnPassCheio.setText("X");
                viatura.setCombustivelPassado("5");
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });

        txtInformacoesVtrObs.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                viatura.setObs(txtInformacoesVtrObs.getText().toString());
                myDB.atualizarViatura(viatura,String.valueOf(viatura.get_id()));
            }
        });
        return view;
    }


    public void CarregarInformacoesViatura(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(getContext());
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
        txtInformacoesVtrObs.setText(viatura.getObs());

        try{

            inicio = Integer.parseInt(viatura.getKmInicio().trim());
            termino = Integer.parseInt(viatura.getKmTermino().trim());

            rodado = termino-inicio;
            txtInformacoesKmRodados.setText(String.valueOf(rodado));
        }catch (NumberFormatException e){
            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
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



}
