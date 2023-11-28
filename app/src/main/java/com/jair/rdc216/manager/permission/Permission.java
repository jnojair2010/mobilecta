package com.jair.rdc216.manager.permission;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    private static int permissaoVerificado = 2;

    private static boolean jaAutorizouAoMenosUmaVez = false;
    private static List<String> listaPermissao = new ArrayList<>();

    private static  List<String> listaPermissoesNegada = new ArrayList<>();


    public static boolean validarPermission(int requestCode, Activity activity, String[] permission, String msg){

        verificarPermissaoMultipla(activity, permission);
        verificarPermissoesNegadaMultipla(activity, permission);
        solicitarPermissoesMultiplas(activity, listaPermissao, msg);
        solicitarPermissaoNegadoMUltipla(activity, listaPermissoesNegada, msg);



        return true;
    }

    private static boolean verificarPermissaoMultipla(Activity activity, String[] permission){

        //for que percorre as permissoes passadass e verifica se ja tem permissoes concedida pelo usuario
        for(String permissao : permission){
            Boolean checkPermission = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;

                    // verifica se as permissoas  está concedida e não negada, ou seja checkagem pela primeira vez e  retorna falso
            Log.i("/////permission/////", " verifica se as permissoas está concedida : "+permissao +"  "+checkPermission);

            if(checkPermission == false){

                //adiciona as permissões na listaPermission para ser solicitada
                listaPermissao.add(permissao);

            }

        };
        return true;
    }

    // metodo que verifica se cada uma dessas permissoes já foram negada
    private static boolean verificarPermissoesNegadaMultipla(Activity activity, String[] permission){

        for(String permissao :permission){
                ActivityCompat.shouldShowRequestPermissionRationale(activity, permissao);
                jaAutorizouAoMenosUmaVez =  ActivityCompat.shouldShowRequestPermissionRationale(activity,permissao);

                if(jaAutorizouAoMenosUmaVez == true){
                    listaPermissoesNegada.add(permissao);
                        Log.i("/////permission/////", " verifica se a permissão já foi begada uma vez "+permissao +"   "+jaAutorizouAoMenosUmaVez);
                }else {
                    Log.i("/////permission/////", " verifica se a permissão nunca foi negada retona 'false' "+permissao +"   "+jaAutorizouAoMenosUmaVez);
                }
        }

        return true;
    }
    private static boolean solicitarPermissoesMultiplas(Activity activity, List<String> listaPermission, String msg){

        //solicitar permissão pela primeira vez

        String[] permissoes = new String[listaPermission.size()];
        listaPermission.toArray(permissoes);

        for(String permissao : permissoes){
            alert(activity, permissao, msg);
            Log.i("/////permission/////", " Solicita Permissão pela primeira vez "+permissao);
        }

        return true;
    }

    private static boolean solicitarPermissaoNegadoMUltipla(Activity activity, List<String> listaPermission, String msg){


        String[] permissoes = new String[listaPermission.size()];
        listaPermission.toArray(permissoes);

        verificarPermissoesNegadaMultipla(activity, permissoes);

            for(String permissao : permissoes){
                alert(activity, permissao,msg);
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

    private static void alert(Activity activity, String permissao, String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        ActivityCompat.requestPermissions(activity,new String[]{permissao},0);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

     builder.create().show();

    }




}
