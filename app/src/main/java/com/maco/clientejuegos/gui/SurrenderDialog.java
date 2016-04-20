package com.maco.clientejuegos.gui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.NetTask;

import java.util.concurrent.ExecutionException;

import edu.uclm.esi.common.jsonMessages.ErrorMessage;
import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.OKMessage;
import edu.uclm.esi.common.jsonMessages.SurrenderAnnouncement;


/**
 * Created by JorgeLumbreras on 19/4/16.
 */
public class SurrenderDialog extends DialogFragment {

/*
    private String email;
    private int idMatch;

    public SurrenderDialog(Context context, String email, int idMatch){
        super(context);
        this.email=email;
        this.idMatch=idMatch;
    }

*/
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Seguro que desea rendirse?")
                .setTitle("Rendición")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Rendición Aceptada.");

                        abandonarPartida();

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Rendición Cancelada.");
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    public void abandonarPartida(){
        Store store=Store.get();
        SurrenderAnnouncement lma=new SurrenderAnnouncement(store.getUser().getEmail(), store.getIdMatch());
        NetTask task=new NetTask("FinishMatch.action", lma);
        task.execute();

        JSONMessage resultadoFinishmatch= null;
        try {
            resultadoFinishmatch = task.get();
            if (resultadoFinishmatch.getType().equals(ErrorMessage.class.getSimpleName())) {
                ErrorMessage em=(ErrorMessage) resultadoFinishmatch;
                Store.get().toast(em.getText());
            } else if (resultadoFinishmatch.getType().equals(OKMessage.class.getSimpleName())) {
                OKMessage okM=(OKMessage) resultadoFinishmatch;
                //  Intent intent=new Intent(this, LoginActivity.class);
                //  startActivity(intent);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Store.get().toast("Tarea interrumpida: " + e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Store.get().toast("Error en ejecución: " + e.getMessage());
        }
        task=null;
    }


}