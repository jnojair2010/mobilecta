package com.jair.rdc216.fragments.frag_activit_main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jair.rdc216.R;
import com.jair.rdc216.activits.CadastroConsultor;
import com.jair.rdc216.fragments.frag_activit_main.frag_home.FragmentsListaEmpresa;
import com.jair.rdc216.interfacenativo.Clicks;
import com.jair.rdc216.manager.ManagerRegraDeNegocio;
import com.jair.rdc216.manager.ManagerUsuarioSistema;
import com.jair.rdc216.model.DatasScheduling;
import com.jair.rdc216.viewmodel.ViewModelFragmentHome;

import java.util.List;

public class Home extends Fragment {
    private List<DatasScheduling> listaAgendamento;

    private View view;
    private ViewHolder mViewHolder = new ViewHolder();
    private FragmentsListaEmpresa mListaEmpresa = new FragmentsListaEmpresa();
    private ViewModelFragmentHome viewModelHome;
    private  FragmentManager fragmentManager;
    private ManagerUsuarioSistema mManagerUsuarioSistema = new ManagerUsuarioSistema();
    private ManagerRegraDeNegocio mManagerRegraDeNegocio = new  ManagerRegraDeNegocio();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view =inflater.inflate(R.layout.fragment_home, container, false);

        this.fragmentManager = getActivity().getSupportFragmentManager();
        checkUserSistem();

        return view;
    }

    public Home(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void getFragmentsListaEmpresa(){


      this.fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_home,mListaEmpresa)
                        .commit();
    }


    @Override
    public void onStart() {
        super.onStart();
        FragmentsListaEmpresa mListaEmpresa1 = new FragmentsListaEmpresa();
        this.fragmentManager.beginTransaction()
                .replace(R.id.frame_layout_home,mListaEmpresa1)
                .commit();

        Log.i("//////////// onPause /////////", "chegou no onstart nivel 2");

    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i("//////////// onPause /////////", "chegou no on pause da fragments nivel 2");
    }


    private static class ViewHolder{
        RecyclerView mRecycleView;

    }
    private void checkUserSistem(){

        boolean logged =  mManagerUsuarioSistema.getUsuarioLogado();
        if(logged != true){

            dialogShow();

        }else{

            getFragmentsListaEmpresa();
        }

    }

    private Dialog dialogShow(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        alertDialog.setView(inflater.inflate(R.layout.alert_dialog_login_primary, null))
                .setCancelable(false)
                .setPositiveButton("Logar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Novo Cadastro", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        abrirActivitCadastroConsultor();
                    }
                });
        return alertDialog.show();
    }

    private void abrirActivitCadastroConsultor(){
        Intent intent = new Intent(getActivity(), CadastroConsultor.class);
            startActivity(intent);
    }




}
