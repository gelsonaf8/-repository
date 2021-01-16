package com.example.CadastroOcorrencias.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.CadastroOcorrencias.model.Abordado;

import java.util.ArrayList;
import java.util.List;

public class AbordadoDAO extends MyDatabaseHelper{


    public AbordadoDAO(@Nullable Context context) {
        super(context);
    }


  public boolean salvarAbordado(Abordado abordado){
        SQLiteDatabase db = getWritableDatabase();
      ContentValues cv = new ContentValues();
      try {
          cv.put("foto",abordado.getFoto());
          cv.put("nome",abordado.getNome());
          cv.put("rg",abordado.getRg());
          cv.put("vulgo",abordado.getVulgo());
          cv.put("enderecoAbordagem",abordado.getEnderecoAbordagem());
          cv.put("qtrAbordagem",abordado.getAtrAbordagem());
          cv.put("dataAbordagem",abordado.getDataAbordagem());
          cv.put("artigos",abordado.getArtigos());
          cv.put("tatuagens",abordado.getTatuagem());
          cv.put("tatoo1",abordado.getTatoo1());
          cv.put("tatoo2",abordado.getTatoo2());
          cv.put("tatoo3",abordado.getTatoo3());
          cv.put("tatoo4",abordado.getTatoo4());
          cv.put("tatoo5",abordado.getTatoo5());
          cv.put("tatoo6",abordado.getTatoo6());
          cv.put("Observacao",abordado.getObservacao());
          db.insert("Abordados",null,cv);
          return true;
      }catch (Exception e){
          Log.i("INFO","erro ao cadastrar!" + e.getMessage());
      }

      return false;
  }



  public List<Abordado> RecuperarAbordados(){
        List<Abordado> ListaAbordados = new ArrayList<>();

        Cursor cursor = null;
        SQLiteDatabase db = getReadableDatabase();
        if(db!= null){
            cursor = db.rawQuery("SELECT * FROM Abordados",null);


        }
        while (cursor.moveToNext()){
            Abordado abordado = new Abordado();
            abordado.setId(cursor.getInt(0));
            abordado.setFoto(cursor.getBlob(1));
            abordado.setNome(cursor.getString(2));
            abordado.setRg(cursor.getString(3));
            abordado.setVulgo(cursor.getString(4));
            abordado.setEnderecoAbordagem(cursor.getString(5));
            abordado.setAtrAbordagem(cursor.getString(6));
            abordado.setDataAbordagem(cursor.getString(7));
            abordado.setArtigos(cursor.getString(8));
            abordado.setTatuagem(cursor.getString(9));
            abordado.setTatoo1(cursor.getBlob(10));
            abordado.setTatoo2(cursor.getBlob(11));
            abordado.setTatoo3(cursor.getBlob(12));
            abordado.setTatoo4(cursor.getBlob(13));
            abordado.setTatoo5(cursor.getBlob(14));
            abordado.setTatoo6(cursor.getBlob(15));
            abordado.setObservacao(cursor.getString(16));
            ListaAbordados.add(abordado);
        }



        return ListaAbordados;
  }

}
