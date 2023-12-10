package com.jair.rdc216.activits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.accounts.AccountManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.jair.rdc216.dao.http.RetrofitHttp;
import com.jair.rdc216.dao.http.ServiceHttp;
import com.jair.rdc216.dao.sqlite.model.ConsultorModel;
import com.jair.rdc216.dao.sqlite.repositorio.ConsultorRespostorio;
import com.jair.rdc216.dao.sqlite.repositorio.LoginRepositorio;
import com.jair.rdc216.databinding.ActivityCadastroConsultorBinding;
import com.jair.rdc216.manager.ManagerUsuarioSistema;
import com.jair.rdc216.manager.permission.Permission;
import com.jair.rdc216.model.Consultor;
import com.jair.rdc216.model.Login;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroConsultor extends AppCompatActivity {
    private ActionBar bar;
    private FragmentManager fragmentManager;
    private GoogleSignInClient mGoogleSingInClient; // precisa adciona a dependencia (implementation 'com.google.android.gms:play-services-auth:20.7.0')
    private ManagerUsuarioSistema mManagerUsuarioSistema = new ManagerUsuarioSistema();
    GoogleSignInClient googleSingInClient;
    FirebaseAuth mAuth;
    AccountManager am;
    private ConsultorModel consultor = new ConsultorModel();

    private LoginRepositorio mLoginRepositorio;
    private ConsultorRespostorio mConsultorRepositorio;

    private ServiceHttp mServiceHttp = RetrofitHttp.createService(ServiceHttp.class);
    private Consultor mConsultor = new Consultor();
   // private ConsultorModel consultor = new ConsultorModel();



    ActivityCadastroConsultorBinding binding;
    private String[] permissioesNecessarias = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCadastroConsultorBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        boolean ok = Permission.validarPermission(1,this,permissioesNecessarias, "Este aplicativo precisa de permissao de localizao para execução sem promblemas");

        mLoginRepositorio = new LoginRepositorio(this);
        mConsultorRepositorio = new ConsultorRespostorio(this);

        this.bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        this.bar.setTitle("Cadastro Consultor");
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b5e20")));



        this.fragmentManager = getSupportFragmentManager();
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("190667746119-gsec08qnrh284vlhuf1q9ipobi7rephf.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSingInClient = GoogleSignIn.getClient(this,gso);


        TextView btnGoolge = (TextView) binding.logarGoogle.getChildAt(0);
        btnGoolge.setText("Fazer o Login com a Google ");

        btnGoolge.setOnClickListener( dados->{
            signIn();
        });

    }


    @Override
    public void onStart(){

        super.onStart();
        if(mManagerUsuarioSistema.getUsuarioLogado()!=true){

        }else{

        }

    }

    private void consultarConsultor(){

        mConsultorRepositorio.getConsultor();

    }

    //salvar o login no banco de dado da conta google
    private void salvarConsultorBd(ConsultorModel consultor ){

        mConsultorRepositorio.InserirConsultor(consultor);

        Toast.makeText(this,"Entrou em SalvarLoginBd:"+consultor.getEmail(),Toast.LENGTH_LONG).show();

    }

    private void salvarConsultorServidor(ConsultorModel consultor ){
        Call<ConsultorModel> salvarEmailSmartPhone = this.mServiceHttp.salvarEmailSmartPhone(consultor.getEmail(), consultor.getData_cadastro());
                    salvarEmailSmartPhone.enqueue(new Callback<ConsultorModel>() {
                        @Override
                        public void onResponse(Call<ConsultorModel> call, Response<ConsultorModel> response) {
                            response.code();
                            Log.i("retrofit"," o status do servidor é "+response.body().getMensagemRetornoServidor());
                        }

                        @Override
                        public void onFailure(Call<ConsultorModel> call, Throwable t) {
                            Log.i("retrofit"," o erro do servidor é "+t.getMessage());
                        }
                    });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signIn(){
        Intent intent = googleSingInClient.getSignInIntent();
        startActivityForResult(intent,1);
    }

    private void loginComGoogle(String token){
        AuthCredential credencial = GoogleAuthProvider.getCredential(token, null);
        mAuth.signInWithCredential(credencial).addOnCompleteListener(this, task->{
            if(task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();

                //pega a data para registrar da cada de cadastro
                Date data = new Date();
                SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = formataData.format(data);

                consultor.setEmail(user.getEmail());
                consultor.setData_cadastro(dataFormatada);

                this.mManagerUsuarioSistema.setConsultor(consultor);

                this.mManagerUsuarioSistema.setUsuarioLogado(true);
                this.mManagerUsuarioSistema.salvarLoginGoogle();

                salvarConsultorBd(consultor);
                salvarConsultorServidor(consultor);

               // Toast.makeText(this,"Login com sucesso  "+user.getEmail(),Toast.LENGTH_LONG).show();
                finish();

            }else{
                Toast.makeText(this,"Não foi possível efetuar o Login",Toast.LENGTH_LONG).show();

            }

        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode ==1){
                Task< GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                try {
                    GoogleSignInAccount conta = task.getResult(ApiException.class);
                    loginComGoogle(conta.getIdToken());
                  //  Toast.makeText(this," Login g efetuado sucesso onActivityResult ",Toast.LENGTH_LONG).show();

                }catch (ApiException execption){
                    String s  = execption.toString();
                    Toast.makeText(this,"Nenhum Usuario Logado no aparelho ",Toast.LENGTH_LONG).show();
                    Log.i("googleErrocount","o erro foi "+s);
                }
        }
    }





}