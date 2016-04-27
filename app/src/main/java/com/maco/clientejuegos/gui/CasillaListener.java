package com.maco.clientejuegos.gui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.NetTask;

import sudokus.SendMovementMessage;

/**
 * Created by Jaime on 19/04/2016.
 */
public class CasillaListener implements TextWatcher {

    private EditText casilla;
    private int pos;
    private EditText[] board;
    private long time;

    public CasillaListener(EditText editText, int pos, EditText[] board, long time) {
        this.casilla = editText;
        this.pos = pos;
        this.board=board;
        this.time=time;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //calculamos el tiempo
        long tiempo_final = System.currentTimeMillis();
        int timeTotal = (int)(tiempo_final - time)/1000;
        //creamos el String de tablero
        String cadenaBoard="";
        for(int i=0;i<board.length;i++){
            if(String.valueOf(board[i].getText()).equalsIgnoreCase(""))
                cadenaBoard=cadenaBoard+"0";
            else
                cadenaBoard=cadenaBoard+""+board[i].getText().toString();
        }

        String valor= String.valueOf(this.casilla.getText());
        SendMovementMessage smm=new SendMovementMessage(Store.get().getUser().getEmail(), Store.get().getIdMatch(), pos,valor, cadenaBoard, timeTotal);
        NetTask task=new NetTask("SendMovement.action", smm);
        task.execute();

    }
}
