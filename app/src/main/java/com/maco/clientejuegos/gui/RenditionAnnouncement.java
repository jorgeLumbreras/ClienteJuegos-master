package com.maco.clientejuegos.gui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


/**
 * Created by JorgeLumbreras on 19/4/16.
 */
public class RenditionAnnouncement extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Seguro que desea rendirse?")
                .setTitle("Rendición")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Rendición Aceptada.");

                        PartidaActivity pa = new PartidaActivity();
                        pa.abandonarPartida(getView());
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


}