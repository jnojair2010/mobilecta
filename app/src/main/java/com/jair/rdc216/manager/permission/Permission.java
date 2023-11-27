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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permission {

    private static int permissaoVerificado = 2;

    private static boolean jaAutorizouAoMenosUmaVez = false;
    private static List<String> listaPermissao = new ArrayList<>();

    private static  List<String> listaPermissoesNegada = new ArrayList<>();


    public static boolean validarPermission(int requestCode, Activity activity, String[] permission){

        verificarPermissaoMultipla(activity, permission);
        verificarPermissoesNegadaMultipla(activity, permission);
        solicitarPermissoesMultiplas(activity, permission);
        solicitarPermissaoNegadoMUltipla(activity, permission);



        return true;
    }

    private static boolean verificarPermissaoMultipla(Activity activity, String[] permission){

        //for que percorre as permissoes passas e verifica se as tem permissoes concedida pelo usuario
        for(String permissao : permission){
            Boolean checkPermission = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;

            // verifica se a permissoa no não já foi concedida, as que foram concedida não é enviada a arraylist
            if(checkPermission == false){

                //envia cara permissoes para a ArrayList
                listaPermissao.add(permissao);
                Log.i("/////permission/////", " Verificação se já existe uma permissão: "+permissao+ " mais a scheckagem "+checkPermission);
            }

        };
        return true;
    }
    private static boolean verificarPermissoesNegadaMultipla(Activity activity, String[] permission){

        for(String permissao :permission){
                ActivityCompat.shouldShowRequestPermissionRationale(activity, permissao);
                jaAutorizouAoMenosUmaVez =  ActivityCompat.shouldShowRequestPermissionRationale(activity,permissao);
                //Log.i("/////permission/////", " verificada as permissoes negada "+jaAutorizouAoMenosUmaVez);

                if(jaAutorizouAoMenosUmaVez == true){
                    listaPermissoesNegada.add(permissao);
                        Log.i("/////permission/////", " verificada as permissoes negada "+jaAutorizouAoMenosUmaVez);
                }
        }

        return true;
    }
    private static boolean solicitarPermissoesMultiplas(Activity activity, String[] permission){

        //solicitar permissão pela primeira vez
        for(String permissao : permission){
            ActivityCompat.requestPermissions(activity,new String[]{permissao},0);
            Log.i("/////permission/////", " permissoes solicitadas "+permissao);
        }

        return true;
    }

    private static boolean solicitarPermissaoNegadoMUltipla(Activity activity, String[] permission){

        verificarPermissoesNegadaMultipla(activity, permission);

            for(String permissoes : listaPermissoesNegada){
                ActivityCompat.requestPermissions(activity,new String[]{permissoes},0);
                Log.i("/////permission/////", " Solicitadas as permissoes que já foram negada "+permissoes);
            }

        return true;

    }





    // verifica de exite permissoa
    private static boolean verificaPermissãoIndividual(Activity activity){
              //verifica se ja existe uma permissão
             // se não está permitido retorna 0
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissaoVerificado = PackageManager.PERMISSION_GRANTED;
            Log.i("/////permission/////", " Verificação se já existe uma permissão: "+permissaoVerificado);

        }
    return true;
    }

    private static boolean verificaPermissaoAutorizadoOuNegado(Activity activity){

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
