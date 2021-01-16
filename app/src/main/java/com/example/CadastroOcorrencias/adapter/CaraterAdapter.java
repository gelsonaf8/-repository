package com.example.CadastroOcorrencias.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.activity.Cadastrar_Carater;
import com.example.CadastroOcorrencias.activity.Cadastrar_Ocorrencias;
import com.example.CadastroOcorrencias.activity.Carater_Inicial;
import com.example.CadastroOcorrencias.model.Carater;
import com.example.CadastroOcorrencias.model.Endereco;

import java.util.List;

public class CaraterAdapter extends RecyclerView.Adapter<CaraterAdapter.myViewHolder> {
    List<Carater> ListaCarater;
    Context context;
    Activity activity;

    public CaraterAdapter(List<Carater> listaCarater,Context context,Activity activity) {
        ListaCarater = listaCarater;
        this.context = context;
        this.activity = activity;
    }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.carater_layout,parent,false);

        return new CaraterAdapter.myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        holder.txtNumeros.setText(ListaCarater.get(position).getNumeros());
        holder.txtLetras.setText(ListaCarater.get(position).getLetras());
        holder.txtModelo.setText(ListaCarater.get(position).getModelo());
        holder.txtCor.setText(ListaCarater.get(position).getCor());
        holder.txtAno.setText(ListaCarater.get(position).getAno());
        holder.txtArea.setText(ListaCarater.get(position).getArea());
        holder.txtNatureza.setText(ListaCarater.get(position).getNatureza());

        holder.caraterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Cadastrar_Carater.class);
                intent.putExtra("Carater",  ListaCarater.get(position));
                activity.startActivityForResult(intent,1);

            }
        });


    }


    @Override
    public int getItemCount() {
        return ListaCarater.size();
    }

    public class  myViewHolder extends RecyclerView.ViewHolder{
        TextView txtNumeros,txtLetras,txtModelo,txtAno,txtCor,txtArea,txtNatureza;
        LinearLayout caraterLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNumeros = itemView.findViewById(R.id.txtNumero);
            txtLetras = itemView.findViewById(R.id.txtLetras);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtCor = itemView.findViewById(R.id.txtCor);
            txtAno = itemView.findViewById(R.id.txtAno);
            txtArea = itemView.findViewById(R.id.txtArea);
            txtNatureza = itemView.findViewById(R.id.txtFR);
            caraterLayout = itemView.findViewById(R.id.caraterLayout);

        }
    }
}
