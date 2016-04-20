package com.maco.clientejuegos.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.NetTask;

import java.util.concurrent.ExecutionException;

import edu.uclm.esi.common.jsonMessages.ErrorMessage;
import edu.uclm.esi.common.jsonMessages.SurrenderAnnouncement;
import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.OKMessage;

public class WarningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
    }
    public void abandonarPartida(View view){
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
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
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

    public void atras(View view){
        super.onBackPressed();
    }




}
