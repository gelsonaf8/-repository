package com.example.CadastroOcorrencias.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.CadastroOcorrencias.R;
import com.example.CadastroOcorrencias.fragments.acessoriosFragment;
import com.example.CadastroOcorrencias.fragments.avariasFragment;
import com.example.CadastroOcorrencias.fragments.funcionamentoFragment;
import com.example.CadastroOcorrencias.fragments.informacoesFragment;
import com.example.CadastroOcorrencias.fragments.outrosFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class ViaturaActivity extends AppCompatActivity {

    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viatura2);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.fundo));

        viewPager = findViewById(R.id.viewPager);
        smartTabLayout =  findViewById(R.id.viewPagerTab);


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Informações", informacoesFragment.class)
                .add("Acessorios", acessoriosFragment.class)
                .add("Funcionamento", funcionamentoFragment.class)
                .add("Avarias", avariasFragment.class)
                .add("Outros", outrosFragment.class)

                .create());


        viewPager.setAdapter(adapter);

        smartTabLayout.setViewPager(viewPager);
    }
}