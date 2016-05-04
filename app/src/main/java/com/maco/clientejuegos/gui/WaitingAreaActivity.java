package com.maco.clientejuegos.gui;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.MessageRecoverer;
import com.maco.clientejuegos.http.NetTask;

import java.util.concurrent.ExecutionException;

import edu.uclm.esi.common.jsonMessages.ErrorMessage;
import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.JoinGameMessage;
import edu.uclm.esi.common.jsonMessages.LoginMessageAnnouncement;
import edu.uclm.esi.common.jsonMessages.LogoutMessageAnnouncement;
import edu.uclm.esi.common.jsonMessages.LogoutWaitingMessage;
import edu.uclm.esi.common.jsonMessages.OKMessage;
import edu.uclm.esi.common.jsonMessages.SudokuBoardMessage;


public class WaitingAreaActivity extends AppCompatActivity implements IMessageDealerActivity {
    private LinearLayout layout;
    private  MessageRecoverer messageRecoverer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_area);
        this.layout=(LinearLayout) findViewById(R.id.layoutWA);
        Store.get().setCurrentContext(this);
        Store store=Store.get();
        //Store.get().lanzarRecuperadorDeMensajes(this);
        messageRecoverer = MessageRecoverer.get(this);
        messageRecoverer.setActivity(this);
        messageRecoverer.setEmailUser(Store.get().getUser().getEmail());
        messageRecoverer.proseguir();
        Thread t = new Thread(messageRecoverer);
        t.start();

        JoinGameMessage jgm = new JoinGameMessage(store.getUser().getIdUser(), 3); // 3 es el codigo de juego sudoku

        NetTask task=new NetTask("JoinGame.action", jgm);
        task.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Store.get().hayQueVolver())
            finish();
    }

    @Override
    public void showMessage(JSONMessage jsm) {
        if(jsm.getType().equals(LoginMessageAnnouncement.class.getSimpleName())) {
            TextView tv = new TextView(this);
            tv.setText("Ha llegado " + ((LoginMessageAnnouncement) jsm).getEmail());
            this.layout.addView(tv);
        }

        if(jsm.getType().equals(SudokuBoardMessage.class.getSimpleName())){
            SudokuBoardMessage sbm = (SudokuBoardMessage) jsm;
            String casillas = sbm.getBoard();
            Store.get().setMatch(sbm.getIdMatch());
            Intent intent = new Intent(this, PartidaActivity.class);
            intent.putExtra("board", casillas);
            intent.putExtra("Jugador1: ", sbm.getUser1());
            intent.putExtra("Jugador2: ", sbm.getUser2());
            startActivity(intent);
            messageRecoverer.detener();
        }

    }

    public void abandonarEspera(View view) {
        Store store=Store.get();
        LogoutWaitingMessage lom=new LogoutWaitingMessage(store.getUser().getEmail(), store.getIdMatch(),true);
        NetTask task=new NetTask("LogoutWaiting.action", lom);
        task.execute();

        JSONMessage resultadoLogoutWaiting= null;
        try {
            resultadoLogoutWaiting = task.get();
            if (resultadoLogoutWaiting.getType().equals(ErrorMessage.class.getSimpleName())) {
                ErrorMessage em=(ErrorMessage) resultadoLogoutWaiting;
                Store.get().toast(em.getText());
            } else if (resultadoLogoutWaiting.getType().equals(OKMessage.class.getSimpleName())) {
                OKMessage okM=(OKMessage) resultadoLogoutWaiting;
                finish();
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

    @Override
    public void onBackPressed() {
        Store store=Store.get();
        LogoutWaitingMessage lom=new LogoutWaitingMessage(store.getUser().getEmail(), store.getIdMatch(),true);
        NetTask task=new NetTask("LogoutWaiting.action", lom);
        task.execute();

        JSONMessage resultadoLogoutWaiting= null;
        try {
            resultadoLogoutWaiting = task.get();
            if (resultadoLogoutWaiting.getType().equals(ErrorMessage.class.getSimpleName())) {
                ErrorMessage em=(ErrorMessage) resultadoLogoutWaiting;
                Store.get().toast(em.getText());
            } else if (resultadoLogoutWaiting.getType().equals(OKMessage.class.getSimpleName())) {
                OKMessage okM=(OKMessage) resultadoLogoutWaiting;
                finish();
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
