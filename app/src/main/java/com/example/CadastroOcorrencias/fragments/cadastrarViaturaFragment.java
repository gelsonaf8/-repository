package com.example.CadastroOcorrencias.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Viatura;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class cadastrarViaturaFragment extends Fragment {

    TextInputEditText txtCadastrarVtrPrefixo,txtCadastrarVtrModalidade,txtCadastrarVtrSetor,txtCadastrarVtrKmInicial,txtCadastrarVtrKmFinal;
    FloatingActionButton btnAtualizarDadosVtr;

    Viatura viatura;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastrar_viatura,container,false);
        txtCadastrarVtrPrefixo = view.findViewById(R.id.txtCadastroVtrPrefixo);
        txtCadastrarVtrModalidade = view.findViewById(R.id.txtCadastroVtrModalidade);
        txtCadastrarVtrSetor = view.findViewById(R.id.txtCadastroVtrSetor);
        txtCadastrarVtrKmInicial = view.findViewById(R.id.txtCadastroVtrKmInicial);
        txtCadastrarVtrKmFinal = view.findViewById(R.id.txtCadastroVtrKmFinal);
        btnAtualizarDadosVtr = view.findViewById(R.id.btnAtualizarDadosVtr);

        getAndSetIntentData();

        btnAtualizarDadosVtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDb = new MyDatabaseHelper(getContext());
                viatura.setPrefixo(txtCadastrarVtrPrefixo.getText().toString().trim());
                viatura.setModalidade(txtCadastrarVtrModalidade.getText().toString().trim());
                viatura.setSetor(txtCadastrarVtrSetor.getText().toString().trim());
                viatura.setKmInicio(txtCadastrarVtrKmInicial.getText().toString().trim());
                viatura.setKmTermino(txtCadastrarVtrKmFinal.getText().toString().trim());
                myDb.atualizarViatura(viatura,String.valueOf(1));

                Fragment myFragment = new informacoesFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, myFragment).addToBackStack(null).commit();

            }
        });


        return view;
    }






    void getAndSetIntentData() {
        Bundle dados = getArguments();
        viatura = (Viatura) dados.getSerializable("Viatura");

        //Colocando dados nos Inputs

        txtCadastrarVtrPrefixo.setText(viatura.getPrefixo());
        txtCadastrarVtrModalidade.setText(viatura.getModalidade());
        txtCadastrarVtrSetor.setText(viatura.getSetor());
        txtCadastrarVtrKmInicial.setText(viatura.getKmInicio());
        txtCadastrarVtrKmFinal.setText(viatura.getKmTermino());

    }

}
