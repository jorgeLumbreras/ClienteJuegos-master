package com.maco.clientejuegos.gui;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.Proxy;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import edu.uclm.esi.common.jsonMessages.ErrorMessage;
import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.OKMessage;
import edu.uclm.esi.common.jsonMessages.RegisterMessage;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }
    public void createAccount(View view){
        EditText etEmail= (EditText) findViewById(R.id.etEmail);
        EditText etPwd1= (EditText) findViewById(R.id.etPwd1);
        EditText etPwd2= (EditText) findViewById(R.id.etPwd2);
        String email=etEmail.getText().toString();
        String pwd1=etPwd1.getText().toString();
        String pwd2=etPwd2.getText().toString();
        RegisterTask rt= new RegisterTask(this,email,pwd1,pwd2);
        rt.execute();
        JSONMessage resultado= null;
        try {
            resultado = rt.get();
            if (resultado.getType().equals(ErrorMessage.class.getSimpleName())) {
                ErrorMessage em=(ErrorMessage) resultado;
                Store.get().toast(em.getText());
            } else if (resultado.getType().equals(OKMessage.class.getSimpleName())) {
                Store.get().toast("Bienvenid@, "+ email);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Store.get().toast("Tarea interrumpida: " + e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Store.get().toast("Error en ejecución: " + e.getMessage());
        }

    }

    class RegisterTask extends AsyncTask<Void, Void, JSONMessage> {
        private final Context ctx;
        private String email;
        private String pwd1;
        private String pwd2;

        RegisterTask(Context ctx, String email, String pwd1, String pwd2){
            this.ctx=ctx;
            this.email=email;
            this.pwd1=pwd1;
            this.pwd2=pwd2;
        }

    @Override
        protected JSONMessage doInBackground(Void... voids) {
            RegisterMessage rm=new RegisterMessage(email, pwd1, pwd2);
            Proxy proxy= Proxy.get();
            try {
                JSONMessage resultado=proxy.doPost("Register.action", rm);
                return resultado;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            } catch (ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }

    @Override
        protected void onPostExecute(JSONMessage jsonMessage) {
            super.onPostExecute(jsonMessage);
        }
    }
}
