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
    public CasillaListener(EditText editText, int pos) {
        this.casilla = editText;
        this.pos = pos;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String valor= String.valueOf(this.casilla.getText());
        SendMovementMessage smm=new SendMovementMessage(Store.get().getUser().getEmail(), Store.get().getIdMatch(), pos,valor);
        NetTask task=new NetTask("SendMovement.action", smm);
        task.execute();

    }
}
