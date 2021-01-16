package com.example.CadastroOcorrencias.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.activity.Atualizar_Pessoas_Interresse;
import com.example.CadastroOcorrencias.model.Abordado;

import java.io.ByteArrayInputStream;
import java.util.List;

public class AbordadoAdapter extends RecyclerView.Adapter<AbordadoAdapter.MyViewHolder> {
    Activity activity;
    Context context;
    List<Abordado> listaAbordado;

    public AbordadoAdapter(Activity activity,Context context, List<Abordado> listaAbordado) {
        this.context = context;
        this.listaAbordado = listaAbordado;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.abordados_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Abordado abordado = listaAbordado.get(position);
        if(abordado.getFoto()!= null){
            Bitmap foto = FotoConvertida(abordado.getFoto());
            holder.foto.setImageBitmap(foto);
        }
        holder.txtId.setText(String.valueOf(position+1));
        holder.txtNome.setText(abordado.getNome());
        holder.txtRg.setText(abordado.getRg());
        holder.txtVulgo.setText(abordado.getVulgo());
        holder.txtEndereco.setText(abordado.getEnderecoAbordagem());
        holder.txtQtr.setText(abordado.getAtrAbordagem());
        holder.txtData.setText(abordado.getDataAbordagem());
        holder.txtArtigos.setText(abordado.getArtigos());
        holder.txtTatuagens.setText(abordado.getTatuagem());
        holder.txtObservacao.setText(abordado.getObservacao());
        holder.mainLayoutAbordados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Atualizar_Pessoas_Interresse.class);
                intent.putExtra("abordado",listaAbordado.get(position));

                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaAbordado.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtId,txtNome,txtRg,txtEndereco,txtQtr,txtData,txtArtigos,txtTatuagens,txtObservacao,txtVulgo;
        ImageView foto;
        LinearLayout mainLayoutAbordados;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.txtAbordadoId);
            foto = itemView.findViewById(R.id.foto);
            txtNome = itemView.findViewById(R.id.txtAbordadoNome);
            txtRg = itemView.findViewById(R.id.txtAbordadoRg);
            txtEndereco = itemView.findViewById(R.id.txtAbordadoEndereco);
            txtQtr = itemView.findViewById(R.id.txtAbordadoQtr);
            txtData = itemView.findViewById(R.id.txtAbordadoData);
            txtArtigos = itemView.findViewById(R.id.txtAbordadoArtigos);
            txtTatuagens = itemView.findViewById(R.id.txtAbordadosTatuagens);
            txtObservacao = itemView.findViewById(R.id.txtAbordadosObservacao);
            txtVulgo = itemView.findViewById(R.id.txtAbordadoVulgo);
            mainLayoutAbordados = itemView.findViewById(R.id.abordadoLayout);

        }
    }

    public Bitmap FotoConvertida(byte[] fotoAbordado){

        ByteArrayInputStream imageStream = new ByteArrayInputStream(fotoAbordado);
        Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);

        return imageBitmap;
    }
}
