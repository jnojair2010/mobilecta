package com.jair.rdc216.activits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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
import com.jair.rdc216.databinding.ActivityCadastroConsultorBinding;
import com.jair.rdc216.manager.ManagerUsuarioSistema;

public class CadastroConsultor extends AppCompatActivity {
    private ActionBar bar;
    private FragmentManager fragmentManager;

    private GoogleSignInClient mGoogleSingInClient; // precisa adciona a dependencia (implementation 'com.google.android.gms:play-services-auth:20.7.0')
    private ManagerUsuarioSistema mManagerUsuarioSistema = new ManagerUsuarioSistema();

    GoogleSignInClient googleSingInClient;

    FirebaseAuth mAuth;


    AccountManager am;

    ActivityCadastroConsultorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCadastroConsultorBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);


      
       // binding..setBackgroundColor(Color.parseColor("#004d40"));

        //setContentView(R.layout.activity_cadastro_consultor);
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

                this.mManagerUsuarioSistema.setUsuarioLogado(true);
                this.mManagerUsuarioSistema.setEmail(user.getEmail());

                this.mManagerUsuarioSistema.salvarLoginGoogle();
                Toast.makeText(this,"Login com sucesso  "+user.getEmail(),Toast.LENGTH_LONG).show();
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