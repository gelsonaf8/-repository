package com.example.CadastroOcorrencias.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.activity.Cadastrar_Ocorrencias;
import com.example.CadastroOcorrencias.model.Talao;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
   private Context context;
   private Activity activity;
   private ArrayList<Talao> listaDeTaloes;

   Animation translate_anim;



   public CustomAdapter(Activity activity,Context context,
                  ArrayList listaDeTaloes){
        this.activity = activity;
        this.context = context;
        this.listaDeTaloes = listaDeTaloes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.id_txt.setText(String.valueOf(listaDeTaloes.size()-(position)));

       Talao talao = listaDeTaloes.get(position);

       // holder.id_txt.setText(talao.getArquivado());
        holder.talao_txt.setText("T-"+talao.getNtalao());
        if(!talao.getNatureza().isEmpty()){
            holder.txt_codigo.setText(talao.getNatureza().substring(0,3));
        }else{
            holder.txt_codigo.setText("");
        }

        holder.qtrInicio_txt.setText(talao.getQtrInicio());
        holder.txtQtrLocal.setText(talao.getQtrLocal());
        holder.txtQtr1Term.setText(talao.getQtr1Term());
        holder.qtrFinal_txt.setText(talao.getQtrFinal());
        holder.txtKmLocal.setText(talao.getKmLocal());
        holder.txtKm1Term.setText(talao.getKm1Term());
        holder.kmInicio_txt.setText(talao.getKmInicio());
        holder.kmFinal_txt.setText(talao.getKmFinal());
        holder.endereco_txt.setText(talao.getEndereco());
        holder.natureza_txt.setText(talao.getNatureza());
        holder.data_txt.setText(talao.getData());
        holder.resultado_txt.setText(talao.getResultado());
        holder.observacao_txt.setText(talao.getObservacao());
        holder.arquivado_txt.setText(talao.getArquivado());

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Cadastrar_Ocorrencias.class);
                intent.putExtra("Talao",  listaDeTaloes.get(position));
                intent.putExtra("Atualizar",true);
                activity.startActivityForResult(intent,1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listaDeTaloes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id_txt,txt_codigo,talao_txt,qtrInicio_txt,txtQtrLocal,txtQtr1Term,qtrFinal_txt,kmInicio_txt,txtKmLocal,txtKm1Term,kmFinal_txt,endereco_txt,natureza_txt,data_txt,resultado_txt,observacao_txt,arquivado_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.txt_id);
            txt_codigo = itemView.findViewById(R.id.txtCodigo);
            talao_txt = itemView.findViewById(R.id.txt_talao);
            qtrInicio_txt = itemView.findViewById(R.id.txt_Qtr_inicio);
            txtQtrLocal = itemView.findViewById(R.id.txtQtrLocal);
            txtQtr1Term = itemView.findViewById(R.id.txtQtr1Term);
            qtrFinal_txt = itemView.findViewById(R.id.txt_Qtr_final);
            kmInicio_txt = itemView.findViewById(R.id.txt_Km_inicio);
           txtKmLocal = itemView.findViewById(R.id.txtKmLocal);
           txtKm1Term = itemView.findViewById(R.id.txtKm1Term);
            kmFinal_txt = itemView.findViewById(R.id.txt_Km_final);
            endereco_txt = itemView.findViewById(R.id.txt_endereco);
            natureza_txt = itemView.findViewById(R.id.txt_natureza);
            data_txt = itemView.findViewById(R.id.txtData);
            resultado_txt = itemView.findViewById(R.id.txtResultado);
            observacao_txt = itemView.findViewById(R.id.txtObs);
            arquivado_txt =itemView.findViewById(R.id.txtArquivada);

            mainLayout = itemView.findViewById(R.id.mainLayout);

            //Animar Recyclerview
           /* translate_anim = AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);*/

        }
    }

}
