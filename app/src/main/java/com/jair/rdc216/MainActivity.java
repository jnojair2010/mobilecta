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
import com.jair.rdc216.dao.http.RetrofitHttp;
import com.jair.rdc216.dao.http.ServiceHttp;
import com.jair.rdc216.dao.sqlite.model.ConsultorModel;
import com.jair.rdc216.dao.sqlite.model.LoginModel;
import com.jair.rdc216.dao.sqlite.repositorio.LoginRepositorio;
import com.jair.rdc216.fragments.frag_activit_main.Checked;
import com.jair.rdc216.fragments.frag_activit_main.Checkelist;
import com.jair.rdc216.fragments.frag_activit_main.Home;
import com.jair.rdc216.manager.ManagerUsuarioSistema;
import com.jair.rdc216.manager.permission.Permission;
import com.jair.rdc216.model.Consultor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private ActionBar bar;
    private Home mHome;
    private Checked mChecked;
    private Checkelist mCheckelist;
    private ViewHolder mViewHolder = new ViewHolder();

   private Consultor consultor = new Consultor();
    private LoginRepositorio mLoginRepositorio;

    private ServiceHttp mServiceHttp = RetrofitHttp.createService(ServiceHttp.class);

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

    //metodo que retornat um consultor antes de cadastrar o consulto no bando de dado sqlite
    // se não tiver consultor cadastrado chama a activitycadastro para iniciar procedimento de cadastro
    private void salvarNomeAndSobreNomeConsultor(){
        consultor = mManagerUsuarioSistema.getmConsultor();
        getConsultorbd();
        if(consultor.getNome()== null && consultor.getSobre_nome() == null) getDialogCadNomeAndSobreNome();

    }

    //metodo que retorna do banco de dado sqlite o consultor que estiver dasatrado
    private void getConsultorbd(){
        mManagerUsuarioSistema.getConsultorBd(this);
       if(mManagerUsuarioSistema.getUsuarioLogado() == true) {
           getConsultorHttp();
           this.consultor = mManagerUsuarioSistema.getmConsultor();
       }
    }

    //Metodo para buscar o consultor no servidor apos consultar o banco de dado
    private void getConsultorHttp(){

        Call<Consultor> getConsultorHttp = this.mServiceHttp.getConsultorHttp(this.consultor.getEmail());

                getConsultorHttp.enqueue(new Callback<Consultor>() {
                    @Override
                    public void onResponse(Call<Consultor> call, Response<Consultor> response) {
                        consultor.setNome(response.body().getNome());
                        consultor.setSobre_nome(response.body().getSobre_nome());
                        consultor.setEmail(response.body().getEmail());
                        consultor.setData_cadastro(response.body().getData_cadastro());
                        consultor.setEstado_consultor(response.body().isEstado_consultor());
                        consultor.setCpf(response.body().getCpf());
                        consultor.setDataRegistro(response.body().getRegistro());
                        consultor.setIdConsultor( response.body().getIdConsultor());
                        mManagerUsuarioSistema.setmConsultor(consultor);
                    }

                    @Override
                    public void onFailure(Call<Consultor> call, Throwable t) {

                    }
                });
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

                        ConsultorModel consultor = new ConsultorModel();
                        consultor.setNome(nome);
                        consultor.setSobre_nome(sobreNome);

                          mManagerUsuarioSistema.setConsultorNomeSobreNome(consultor);

                        abrirActivitCadastroConsultor();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
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