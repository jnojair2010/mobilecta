package com.jair.rdc216;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.Firebase;
import com.jair.rdc216.activits.CadastroConsultor;
import com.jair.rdc216.fragments.frag_activit_main.Checked;
import com.jair.rdc216.fragments.frag_activit_main.Checkelist;
import com.jair.rdc216.fragments.frag_activit_main.Home;
import com.jair.rdc216.manager.ManagerUsuarioSistema;
import com.jair.rdc216.manager.permission.Permission;


public class MainActivity extends AppCompatActivity {
    private ActionBar bar;
    private Home mHome;
    private Checked mChecked;
    private Checkelist mCheckelist;
    private ViewHolder mViewHolder = new ViewHolder();

    private String[] permissioesNecessarias = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private ManagerUsuarioSistema mManagerUsuarioSistema = new ManagerUsuarioSistema();


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bar = getSupportActionBar();

        this.bar.setTitle("Sistema Rdc216");
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b5e20")));

        mHome = new Home();
        mChecked = new Checked();
        mCheckelist = new Checkelist();

        // apresenta a permissoa
      //  boolean ok = Permission.validarPermission(1,this,permissioesNecessarias);
        // inplementa o fragmento home de visualizção inicial

            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction1.replace(R.id.flame_layout,mHome);
            fragmentTransaction1.commit();
        // fom da implementação da fragments home

        bottomNavigationView = findViewById(R.id.bottomNavigationViewMain);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.flame_layout,mHome);
                        fragmentTransaction1.commit();

                        return true;
                    case R.id.checked:
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.flame_layout,mChecked);
                        fragmentTransaction2.commit();

                        return true;

                    case R.id.cheklist:
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.flame_layout,mCheckelist);
                        fragmentTransaction3.commit();
                        return true;
                }


                return false;
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_superior, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.cad_empresa:
              //  Intent intent1 = new Intent(this, Cadastros.class);
             //   startActivity(intent1);
                return true;
        }

        return false;
    }
    @Override
    public void onStart(){
        super.onStart();

        boolean logged =  mManagerUsuarioSistema.getUsuarioLogado();
        if(logged != true){
            abrirActivitCadastroConsultor();

        }else{


        }
    }

    private void abrirActivitCadastroConsultor(){
        Intent intent = new Intent(this, CadastroConsultor.class);
        startActivity(intent);
    }



    private static class ViewHolder{
        Button btn_viewToast;
        RecyclerView mRecycleView;

    }


}