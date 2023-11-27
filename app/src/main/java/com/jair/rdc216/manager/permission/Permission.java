package com.jair.rdc216.manager.permission;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permission {

    private static int permissaoVerificado = 2;

    private static boolean jaAutorizouAoMenosUmaVez = false;


    public static boolean validarPermission(int requestCode, Activity activity, String[] permission){

        verificaPermissão(activity);

        VerificaPermissaoAutorizadoOuNegado(activity);
        solicitarPermissaoPelaPrimeiraVez(activity);
        solicitarPermissaoSeNegado(activity);

        return true;
    }

    // verifica de exite permissoa
    private static boolean verificaPermissão(Activity activity){
              //verifica se ja existe uma permissão
             // se não está permitido retorna 0
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissaoVerificado = PackageManager.PERMISSION_GRANTED;
            Log.i("/////permission/////", " Verificação se já existe uma permissão: "+permissaoVerificado);

        }
    return true;
    }

    private static boolean VerificaPermissaoAutorizadoOuNegado(Activity activity){

        // verifica se o usuario já recebeu a notificação de permissão ao menus uma vez e ele já aceitou ou negou
        // se negou retorna true se permitiu retorna false

        if(permissaoVerificado == 0){
            ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.ACCESS_COARSE_LOCATION);
            jaAutorizouAoMenosUmaVez =  ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.ACCESS_COARSE_LOCATION);
            Log.i("/////permission/////", " mostra se o usuario já permitiu ou negou uma vez "+jaAutorizouAoMenosUmaVez);
        }

        return true;
    }

    //solicitar permissão pela primeira vez
    private static boolean solicitarPermissaoPelaPrimeiraVez(Activity activity){
        if(jaAutorizouAoMenosUmaVez == false){
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},0);
            Log.i("/////permission/////", " Solicita a permissão "+jaAutorizouAoMenosUmaVez);
        }
        return true;
    }


    private static boolean solicitarPermissaoSeNegado(Activity activity){

        //solicitar permissão de já foi negado uma vez percistir ate ser autorizado
        if(jaAutorizouAoMenosUmaVez == true){
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},0);
        }
       return true;
    }





}
