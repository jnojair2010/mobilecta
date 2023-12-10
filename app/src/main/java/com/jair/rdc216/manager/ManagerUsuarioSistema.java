package com.jair.rdc216.manager;

import android.content.Context;
import android.util.Log;

import com.jair.rdc216.dao.sqlite.model.ConsultorModel;
import com.jair.rdc216.dao.sqlite.repositorio.ConsultorRespostorio;
import com.jair.rdc216.model.Consultor;

public class ManagerUsuarioSistema {


    public static Consultor Consultor = new Consultor();
   // ConsultorModel consultor = new ConsultorModel();

    private static boolean usuarioLogado = false;
    private static String email;

    public ManagerUsuarioSistema(){

    }

    public static Consultor getmConsultor() {
        return Consultor;
    }

    public void getConsultorBd(Context context){
        ConsultorRespostorio respConsultor = new ConsultorRespostorio(context);
        ConsultorModel mConsultor = new ConsultorModel();
        mConsultor = respConsultor.getConsultor();
        if(mConsultor != null){
             Consultor.setNome(mConsultor.getNome());
              Consultor.setSobre_nome(mConsultor.getSobre_nome());
             Consultor.setEmail(mConsultor.getEmail());
             Consultor.setData_cadastro(mConsultor.getData_cadastro());
        }

    }

    public static void setmConsultor(Consultor mConsultor) {
        ManagerUsuarioSistema.Consultor = mConsultor;
    }

    public void setConsultorEmail(Consultor consultor) {
        this.Consultor.setEmail(consultor.getEmail());
        this.Consultor.setData_cadastro(consultor.getData_cadastro());
    }
    public void setConsultorNomeSobreNome(ConsultorModel consultor) {
        this.Consultor.setNome(consultor.getNome());
        this.Consultor.setSobre_nome(consultor.getSobre_nome());
    }

    public boolean getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(boolean usuarioLogado) {
        ManagerUsuarioSistema.usuarioLogado = usuarioLogado;
    }

    // para deletar
  /*  public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ManagerUsuarioSistema.email = email;
    }*/

    public boolean salvarLoginGoogle(){

        if(salvarLoginGoogleSqlite()==true && salvarLoginServido()){

            return true;
        }else {
            return false;
        }
    }

    private boolean salvarLoginGoogleSqlite(){

        return true;
    }
    private boolean salvarLoginServido(){

        return true;
    }


}
