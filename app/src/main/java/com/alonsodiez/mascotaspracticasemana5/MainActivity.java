package com.alonsodiez.mascotaspracticasemana5;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alonsodiez.mascotaspracticasemana5.adapter.MascotaAdaptador;
import com.alonsodiez.mascotaspracticasemana5.adapter.PageAdapter;
import com.alonsodiez.mascotaspracticasemana5.fragment.ListadoFragment;
import com.alonsodiez.mascotaspracticasemana5.fragment.RecyclerView_Fragment;
import com.alonsodiez.mascotaspracticasemana5.pojo.Mascota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    public MascotaAdaptador adaptador;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
        setUpViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mContact:
                Intent iContact = new Intent(this, ActivityContact.class);
                startActivity(iContact);
                break;
            case R.id.mAbout:
                Intent iAbout = new Intent(this, ActivityAbout.class);
                startActivity(iAbout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void paginaFavoritos(View v){
        Intent intent = new Intent(MainActivity.this, MascotasFavoritas.class);
        startActivity(intent);
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerView_Fragment());
        fragments.add(new ListadoFragment());

        return fragments;
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
    }
}
