package com.example.CadastroOcorrencias.fragments;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.helper.MyDatabaseHelper;
import com.example.CadastroOcorrencias.model.Avaria;

import java.util.ArrayList;
import java.util.List;


public class avariasFragment extends Fragment {
    Button av1, av2, av3, av4, av5, av6, av7, av8, av9, av10, av11, av12, av13, av14, av15, av16, av17, av18, av19, av20, av21, av22, av23, av24, av25, av26, av27, av28;
    Avaria avaria;
    View view;

    List<Avaria> listaAvaria;


    // TODO: Rename parameter arguments, choose names that match


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_avarias_viatura, container, false);
        av1 = view.findViewById(R.id.av1);
        av2 = view.findViewById(R.id.av2);
        av3 = view.findViewById(R.id.av3);
        av4 = view.findViewById(R.id.av4);
        av5 = view.findViewById(R.id.av5);
        av6 = view.findViewById(R.id.av6);
        av7 = view.findViewById(R.id.av7);
        av8 = view.findViewById(R.id.av8);

        av9 = view.findViewById(R.id.av9);
        av10 = view.findViewById(R.id.av10);
        av11 = view.findViewById(R.id.av11);
        av12 = view.findViewById(R.id.av12);

        av13 = view.findViewById(R.id.av13);
        av14 = view.findViewById(R.id.av14);
        av15 = view.findViewById(R.id.av15);
        av16 = view.findViewById(R.id.av16);
        av17 = view.findViewById(R.id.av17);
        av18 = view.findViewById(R.id.av18);
        av19 = view.findViewById(R.id.av19);
        av20 = view.findViewById(R.id.av20);
        av21 = view.findViewById(R.id.av21);
        av22 = view.findViewById(R.id.av22);
        av23 = view.findViewById(R.id.av23);
        av24 = view.findViewById(R.id.av24);
        av25 = view.findViewById(R.id.av25);
        av26 = view.findViewById(R.id.av26);
        av27 = view.findViewById(R.id.av27);
        av28 = view.findViewById(R.id.av28);

        CarregarAvarias();


        av1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });
        av14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });
        av20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });

        av28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarOpcoes(v);
            }
        });





        return view;
    }


    public void CarregarAvarias() {
        MyDatabaseHelper myDB = new MyDatabaseHelper(getContext());
        listaAvaria = new ArrayList<>();
        Cursor cursor = myDB.readAllData("Avarias", "T");

        while (cursor.moveToNext()) {
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

    public void MostrarOpcoes(View v) {
        final MyDatabaseHelper MyDB = new MyDatabaseHelper(getContext());
        final Button btn = (Button) v;
        final String[] listaDeAvarias = new String[]{"1 = Amassado", "2 = Riscado", "3 = Quebrado", "4 = Manchado", "5 = Descascado", "6 - Outros", "7 - Limpar"};


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("AVARIAS");

        alertDialogBuilder.setSingleChoiceItems(listaDeAvarias, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (btn.getText().toString().isEmpty()) {
                    btn.setText(listaDeAvarias[position].trim().substring(0, 1));
                } else {
                    String avarias = btn.getText().toString().trim() + "/" + listaDeAvarias[position].trim().substring(0, 1);
                    btn.setText(avarias);
                }
                if (position == 6) {
                    btn.setText("");
                }
                String id = btn.getTag().toString().trim();
                MyDB.atualizarAvarias(btn.getText().toString().trim(), id);
                dialog.dismiss();


            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

    }


}