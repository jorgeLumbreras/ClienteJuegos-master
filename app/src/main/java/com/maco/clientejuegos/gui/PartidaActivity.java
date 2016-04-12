package com.maco.clientejuegos.gui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;

public class PartidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida2);

        Store.get().setCurrentContext(this);
        Store store=Store.get();
        //con esto obtengo el tablero que lleva el intent
        String b=(String)this.getIntent().getExtras().get("board");
        char[] cadena=b.toCharArray();
        EditText[][] casillas1;
        EditText[][] casillas2;
        EditText prueba=(EditText) findViewById(R.id.editText0);



      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
    }

}
