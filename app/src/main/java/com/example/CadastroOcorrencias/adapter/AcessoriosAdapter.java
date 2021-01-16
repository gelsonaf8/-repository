package com.example.CadastroOcorrencias.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Acessorios;

import java.util.List;

public class AcessoriosAdapter extends RecyclerView.Adapter<AcessoriosAdapter.MyViewHolder> {
    List<Acessorios> ListaAcessorios;
    Context context;
    Activity activity;

    public AcessoriosAdapter(List<Acessorios> listaAcessorios,Context context,Activity activity) {
        ListaAcessorios = listaAcessorios;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.acessorios_viatura,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.txtDescricao.setText(ListaAcessorios.get(position).getDescricao());
        if(ListaAcessorios.get(position).getSim().equals("true")){
            holder.btnSim.setText("X");
        }
        if(ListaAcessorios.get(position).getSim().equals("false")){
            holder.btnNao.setText("X");
        }
        if(ListaAcessorios.get(position).getSim().equals("")){
            holder.btnSim.setText("");
        }

        holder.btnSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.btnSim.getText().toString().isEmpty()){

                    ListaAcessorios.get(position).setSim("");
                    holder.btnSim.setText("");
                }else {
                    holder.btnSim.setText("X");
                    ListaAcessorios.get(position).setSim("true");
                    holder.btnNao.setText("");
                }

                MyDatabaseHelper myDb = new MyDatabaseHelper(context);
                myDb.atualizarAcessorios(ListaAcessorios.get(position),String.valueOf(ListaAcessorios.get(position).getId()));


            }
        });

        holder.btnNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.btnNao.getText().toString().isEmpty()){

                    ListaAcessorios.get(position).setSim("");
                    holder.btnNao.setText("");
                }else {
                    holder.btnSim.setText("");
                    ListaAcessorios.get(position).setSim("false");
                    holder.btnNao.setText("X");
                }


                MyDatabaseHelper myDb = new MyDatabaseHelper(context);
                myDb.atualizarAcessorios(ListaAcessorios.get(position),String.valueOf(ListaAcessorios.get(position).getId()));

            }
        });


    }

    @Override
    public int getItemCount() {
        return ListaAcessorios.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtDescricao;
        Button btnSim,btnNao;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            btnSim = itemView.findViewById(R.id.btnSim);
            btnNao = itemView.findViewById(R.id.btnNao);

        }
    }
}
