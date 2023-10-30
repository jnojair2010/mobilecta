package com.jair.rdc216.manager;

import com.jair.rdc216.model.Consultor;

public class ManagerUsuarioSistema {

    private static Consultor mConsultor = new Consultor();


    public static Consultor getmConsultor() {
        return mConsultor;
    }

    public static void setmConsultor(Consultor mConsultor) {
        ManagerUsuarioSistema.mConsultor = mConsultor;
    }
}
