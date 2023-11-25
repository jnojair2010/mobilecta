package com.jair.rdc216.manager.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    public static boolean validarPermission(int requestCode, Activity activity, String[] permission){

        if(Build.VERSION.SDK_INT>=23){

                List<String> listaPermissiao = new ArrayList<>();

                for(String permissao:permission){
                     Boolean validaPermissoa =   ContextCompat.checkSelfPermission(activity,permissao) == PackageManager.PERMISSION_GRANTED;

                     if(!validaPermissoa) listaPermissiao.add(permissao);
                }
                String[] novaPermissao = new String[listaPermissiao.size()];
                listaPermissiao.toArray(novaPermissao);

                 if(listaPermissiao.isEmpty()) return true;

            ActivityCompat.requestPermissions(activity, novaPermissao, requestCode);

        }

        return true;
    }

}
