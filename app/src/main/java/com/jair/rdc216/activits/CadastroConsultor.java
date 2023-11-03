package com.jair.rdc216.activits;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.jair.rdc216.R;

public class CadastroConsultor extends AppCompatActivity {
    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_consultor);
        this.bar = getSupportActionBar();

        this.bar.setTitle("Cadastro Consultor");
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b5e20")));

    }

}