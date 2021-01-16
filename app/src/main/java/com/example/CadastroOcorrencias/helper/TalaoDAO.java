package com.example.CadastroOcorrencias.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.CadastroOcorrencias.model.Talao;

import java.util.List;

public class TalaoDAO  extends MyDatabaseHelper{

    public TalaoDAO(@Nullable Context context) {

        super(context);

    }

    public void AdicionarTalao(Talao talao, Context context){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNA_NTALAO,talao.getNtalao());
        cv.put(COLUNA_QTR_INICIO,talao.getQtrInicio());
        cv.put(COLUNA_QTR_LOCAL,talao.getQtrLocal());
        cv.put(COLUNA_QTR_1TERM,talao.getQtr1Term());
        cv.put(COLUNA_QTR_FINAL,talao.getQtrFinal());
        cv.put(COLUNA_KM_INICIO,talao.getKmInicio());
        cv.put(COLUNA_KM_LOCAL,talao.getKmLocal());
        cv.put(COLUNA_KM_1TERM,talao.getKm1Term());
        cv.put(COLUNA_KM_FINAL,talao.getKmFinal());
        cv.put(COLUNA_ENDERECO,talao.getEndereco());
        cv.put(COLUNA_NATUREZA,talao.getNatureza());
        cv.put(COLUNA_DATA,talao.getData());
        cv.put(COLUNA_RESULTADO,talao.getResultado());
        cv.put(COLUNA_OBESERVACAO,talao.getObservacao());
        cv.put(COLUNA_ARQUIVADO,talao.getArquivado());

        long retorno = db.insert(TABLE_NAME,null,cv);
        if(retorno == -1){
            Toast.makeText(context,"Falhou",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Adicionado com sucesso!",Toast.LENGTH_SHORT).show();
        }

    }

    public void updateData(Talao talao,Context context){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUNA_NTALAO,talao.getNtalao());
        cv.put(COLUNA_QTR_INICIO,talao.getQtrInicio());
        cv.put(COLUNA_QTR_LOCAL,talao.getQtrLocal());
        cv.put(COLUNA_QTR_1TERM,talao.getQtr1Term());
        cv.put(COLUNA_QTR_FINAL,talao.getQtrFinal());
        cv.put(COLUNA_KM_INICIO,talao.getKmInicio());
        cv.put(COLUNA_KM_LOCAL,talao.getKmLocal());
        cv.put(COLUNA_KM_1TERM,talao.getKm1Term());
        cv.put(COLUNA_KM_FINAL,talao.getKmFinal());
        cv.put(COLUNA_ENDERECO,talao.getEndereco());
        cv.put(COLUNA_NATUREZA,talao.getNatureza());
        cv.put(COLUNA_RESULTADO,talao.getResultado());
        cv.put(COLUNA_OBESERVACAO,talao.getObservacao());

        long retorno = db.update("Taloes",cv,"_id=?",new String[]{String.valueOf(talao.getId())});

        if(retorno == -1){
            Toast.makeText(context,"Erro ao atualizar os dados.",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Dados atualizados.",Toast.LENGTH_SHORT).show();

        }


    }

    public void arquivarTalao(Talao talao){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNA_ARQUIVADO,"Arquivado");
        db.update("Taloes",cv,"_id=?",new String[]{String.valueOf(talao.getId())});

    }

    public void arquivarTodos(List<Talao> ListaTalao){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNA_ARQUIVADO,"Arquivado");

        for(int i=0;i<ListaTalao.size();i++){
            db.update("Taloes",cv,"_id=?",new String[]{String.valueOf(ListaTalao.get(i).getId())});
        }

    }

}
