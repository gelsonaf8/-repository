package com.example.CadastroOcorrencias.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Endereco;

import java.util.List;

public class MistoAdapter extends RecyclerView.Adapter<MistoAdapter.MyViewHolder> {
    List<Endereco> ListaEndereco;
    Context context;
    Activity activity;

    public MistoAdapter(List<Endereco> listaEndereco,Context context,Activity activity) {
        ListaEndereco = listaEndereco;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layout_misto,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.txtValorMisto.setText(ListaEndereco.get(position).getNome());
        holder.txtErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDb = new MyDatabaseHelper(context);
                myDb.deleteOneRow(String.valueOf(ListaEndereco.get(position).getId()),"Enderecos");
                activity.recreate();
                Toast.makeText(context, "Dados Apagado", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return ListaEndereco.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtValorMisto,txtErase;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtValorMisto = itemView.findViewById(R.id.txtValorMisto);
            txtErase = itemView.findViewById(R.id.txtErase);

        }
    }
}
