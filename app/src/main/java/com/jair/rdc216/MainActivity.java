package com.jair.rdc216;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.Firebase;
import com.jair.rdc216.activits.CadastroConsultor;
import com.jair.rdc216.dao.sqlite.model.LoginModel;
import com.jair.rdc216.dao.sqlite.repositorio.LoginRepositorio;
import com.jair.rdc216.fragments.frag_activit_main.Checked;
import com.jair.rdc216.fragments.frag_activit_main.Checkelist;
import com.jair.rdc216.fragments.frag_activit_main.Home;
import com.jair.rdc216.manager.ManagerUsuarioSistema;
import com.jair.rdc216.manager.permission.Permission;
import com.jair.rdc216.model.Consultor;


public class MainActivity extends AppCompatActivity {
    private ActionBar bar;
    private Home mHome;
    private Checked mChecked;
    private Checkelist mCheckelist;
    private ViewHolder mViewHolder = new ViewHolder();

    Consultor mConsultor = new Consultor();
    private LoginRepositorio mLoginRepositorio;




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

        //só entra no is se não tiver usuario logado no sistema
       if(mManagerUsuarioSistema.getUsuarioLogado() != true) salvarNomeAndSobreNomeConsultor();
      /* // chama o metodo quando o usuario for logado para verificar se ha nome e sobrenome cadastrado com o fim de salvar esses atributos
       if(mManagerUsuarioSistema.getUsuarioLogado() == true)*/


    }

    private void abrirActivitCadastroConsultor(){
        Intent intent = new Intent(this, CadastroConsultor.class);
        startActivity(intent);
    }

    private void salvarNomeAndSobreNomeConsultor(){
        mConsultor = mManagerUsuarioSistema.getmConsultor();
        if(mConsultor.getNome()== null && mConsultor.getSobre_nome() == null) getDialogCadNomeAndSobreNome();


    }

    private Dialog getDialogCadNomeAndSobreNome(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater Inflater = LayoutInflater.from(MainActivity.this);
        View inflarLayoutInputNome = Inflater.inflate(R.layout.input_nome_sobrenome_dialog, null);

        builder.setView(inflarLayoutInputNome);
                builder.setCancelable(false)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText edtNome = (EditText) inflarLayoutInputNome.findViewById(R.id.edtNome);
                        EditText edtSobreNome = (EditText) inflarLayoutInputNome.findViewById(R.id.edtSobreNome);

                          String nome = edtNome.getText().toString();
                          String sobreNome = edtSobreNome.getText().toString();

                        abrirActivitCadastroConsultor();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        return  builder.show();
    }

    private void atualizarCadastroConsultor(){

    }



    private static class ViewHolder{
        Button btn_viewToast;
        RecyclerView mRecycleView;

    }


}