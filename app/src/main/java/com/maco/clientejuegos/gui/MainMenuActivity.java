package com.maco.clientejuegos.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.Proxy;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Store.get().setCurrentContext(this);
        Store.get().volver(false);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Store.get().volver(false);
    }

    public void login(View view) {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
    public void createAccount(View view){
        Intent intent = new Intent(this,CreateAccountActivity.class);
        startActivity(intent);
    }

    public void records(View view){
        Intent intent = new Intent(this,RecordsActivity.class);
        startActivity(intent);
    }

    public void fijarIP(View view){
        EditText etIPServer=(EditText) this.findViewById(R.id.etIPServer);
        Proxy.get().setUrlServer(etIPServer.getText().toString());
        Store.get().toast("IP fijada con exito");
    }
}
