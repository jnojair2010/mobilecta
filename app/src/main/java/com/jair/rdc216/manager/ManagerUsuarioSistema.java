package com.jair.rdc216.manager;

import com.jair.rdc216.model.Consultor;

public class ManagerUsuarioSistema {

    private static Consultor mConsultor = new Consultor();

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

    public boolean getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(boolean usuarioLogado) {
        ManagerUsuarioSistema.usuarioLogado = usuarioLogado;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ManagerUsuarioSistema.email = email;
    }
}
