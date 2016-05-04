package com.maco.clientejuegos.gui;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Record;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.domain.User;
import com.maco.clientejuegos.http.MessageRecoverer;
import com.maco.clientejuegos.http.NetTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import edu.uclm.esi.common.jsonMessages.ErrorMessage;
import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.JSONable;
import edu.uclm.esi.common.jsonMessages.OKMessage;
import sudokus.GetRecordsMessage;
import sudokus.ListRecordsAnnouncement;

public class RecordsActivity extends AppCompatActivity implements IMessageDealerActivity {

    private ListView lv;
    private MessageRecoverer messageRecoverer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Store.get().setCurrentContext(this);

        this.lv= (ListView) findViewById(R.id.listView);





        GetRecordsMessage grm = new GetRecordsMessage("pin");
        NetTask task = new NetTask("GetRecords.action", grm);
        task.execute();

        JSONMessage resultadoGetRecordsMessage = null;
        try {
            resultadoGetRecordsMessage = task.get();
            if (resultadoGetRecordsMessage.getType().equals(ErrorMessage.class.getSimpleName())) {
                ErrorMessage em = (ErrorMessage) resultadoGetRecordsMessage;
                Store.get().toast(em.getText());
            } else if (resultadoGetRecordsMessage.getType().equals(OKMessage.class.getSimpleName())) {
                OKMessage okM = (OKMessage) resultadoGetRecordsMessage;

                int idUser=123456789;
                User user=new User("invitado@uclm.es", idUser);
                Store.get().setUser(user);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Store.get().toast("Tarea interrumpida: " + e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Store.get().toast("Error en ejecución: " + e.getMessage());
        }
        task = null;



        messageRecoverer = MessageRecoverer.get(this);
        messageRecoverer.setActivity(this);
        messageRecoverer.setEmailUser(Store.get().getUser().getEmail());
        messageRecoverer.proseguir();
        Thread t = new Thread(messageRecoverer);
        t.start();

 //       String[] records = {"1º Eureca", "2º Repampanos", "3º Recorcholis", "1º Eureca", "2º Repampanos", "3º Recorcholis", "1º Eureca", "2º Repampanos", "3º Recorcholis", "1º Eureca", "2º Repampanos", "3º Recorcholis"};

 //       this.lv = (ListView) findViewById(R.id.listView);

 /*       ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, records);
        this.lv.setAdapter(adaptador);
*/
    }

    @Override
    public void showMessage(JSONMessage jsm) {
        if (jsm.getType().equals(ListRecordsAnnouncement.class.getSimpleName())) {
            //convierto el mensaje a listRecordsAnnouncement
            ListRecordsAnnouncement lra = (ListRecordsAnnouncement) jsm;
            // creo la lista de records que quiero rellenar
            LinkedList<Record> records = new LinkedList<Record>();
            // recupero los records del mensaje k recibo
            JSONArray jsa = lra.getRecords();

            if (jsa != null) {
                int len = jsa.length();
                for (int i = 0; i < len; i++) {
                    //recupero cada JSONObject del array y creo un record que meto en la lista
                    try {
                        JSONObject jso = jsa.getJSONObject(i);
                        Record rec = new Record(jso.get("email").toString(), jso.getInt("tiempo"));
                        records.add(rec);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
            String[] items = new String[records.size()];
            for(int i = 0;i<items.length;i++){
                items[i]=""+records.get(i).getTiempo()+"  seg      "+records.get(i).getEmail();
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(RecordsActivity.this, android.R.layout.simple_list_item_1, items);
            this.lv.setAdapter(adaptador);

            messageRecoverer.detener();
        }
    }




}
