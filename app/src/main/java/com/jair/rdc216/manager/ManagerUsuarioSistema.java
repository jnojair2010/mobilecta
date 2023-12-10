package com.jair.rdc216.manager;

import com.jair.rdc216.dao.sqlite.model.ConsultorModel;
import com.jair.rdc216.model.Consultor;

public class ManagerUsuarioSistema {


    public static Consultor mConsultor = new Consultor();
   // ConsultorModel consultor = new ConsultorModel();

    private static boolean usuarioLogado = false;
    private static String email;

    public ManagerUsuarioSistema(){

    }

    public static Consultor getmConsultor() {
        return mConsultor;
    }

    public static void setmConsultor(Consultor mConsultor) {
        ManagerUsuarioSistema.mConsultor = mConsultor;
    }

    public void setConsultor(ConsultorModel consultor) {
        this.mConsultor.setEmail(consultor.getEmail());
        this.mConsultor.setDataRegisto(consultor.getData_cadastro());
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
