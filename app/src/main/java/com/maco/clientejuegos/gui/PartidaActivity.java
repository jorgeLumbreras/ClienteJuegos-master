package com.maco.clientejuegos.gui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.maco.clientejuegos.R;
import com.maco.clientejuegos.domain.Store;
import com.maco.clientejuegos.http.MessageRecoverer;
import com.maco.clientejuegos.http.NetTask;

import java.util.concurrent.ExecutionException;

import edu.uclm.esi.common.jsonMessages.ErrorMessage;
import edu.uclm.esi.common.jsonMessages.SurrenderAnnouncement;
import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.OKMessage;
import sudokus.SendMovementMessage;

public class PartidaActivity extends AppCompatActivity implements IMessageDealerActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;

    private int posicion=0;
    private EditText[] casillas1;
    private EditText[] casillas2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida2);


        //con esto obtengo el tablero que lleva el intent
        String b = (String) this.getIntent().getExtras().get("board");
        char[] cadena = b.toCharArray();
        casillas1 = new EditText[81];
        casillas2 = new EditText[81];

        casillas1[0] = (EditText) findViewById(R.id.editText0);
        casillas1[1] = (EditText) findViewById(R.id.editText1);
        casillas1[2] = (EditText) findViewById(R.id.editText2);
        casillas1[3] = (EditText) findViewById(R.id.editText3);
        casillas1[4] = (EditText) findViewById(R.id.editText4);
        casillas1[5] = (EditText) findViewById(R.id.editText5);
        casillas1[6] = (EditText) findViewById(R.id.editText6);
        casillas1[7] = (EditText) findViewById(R.id.editText7);
        casillas1[8] = (EditText) findViewById(R.id.editText8);
        casillas1[9] = (EditText) findViewById(R.id.editText9);
        casillas1[10] = (EditText) findViewById(R.id.editText10);
        casillas1[11] = (EditText) findViewById(R.id.editText11);
        casillas1[12] = (EditText) findViewById(R.id.editText12);
        casillas1[13] = (EditText) findViewById(R.id.editText13);
        casillas1[14] = (EditText) findViewById(R.id.editText14);
        casillas1[15] = (EditText) findViewById(R.id.editText15);
        casillas1[16] = (EditText) findViewById(R.id.editText16);
        casillas1[17] = (EditText) findViewById(R.id.editText17);
        casillas1[18] = (EditText) findViewById(R.id.editText18);
        casillas1[19] = (EditText) findViewById(R.id.editText19);
        casillas1[20] = (EditText) findViewById(R.id.editText20);
        casillas1[21] = (EditText) findViewById(R.id.editText21);
        casillas1[22] = (EditText) findViewById(R.id.editText22);
        casillas1[23] = (EditText) findViewById(R.id.editText23);
        casillas1[24] = (EditText) findViewById(R.id.editText24);
        casillas1[25] = (EditText) findViewById(R.id.editText25);
        casillas1[26] = (EditText) findViewById(R.id.editText26);
        casillas1[27] = (EditText) findViewById(R.id.editText27);
        casillas1[28] = (EditText) findViewById(R.id.editText28);
        casillas1[29] = (EditText) findViewById(R.id.editText29);
        casillas1[30] = (EditText) findViewById(R.id.editText30);
        casillas1[31] = (EditText) findViewById(R.id.editText31);
        casillas1[32] = (EditText) findViewById(R.id.editText32);
        casillas1[33] = (EditText) findViewById(R.id.editText33);
        casillas1[34] = (EditText) findViewById(R.id.editText34);
        casillas1[35] = (EditText) findViewById(R.id.editText35);
        casillas1[36] = (EditText) findViewById(R.id.editText36);
        casillas1[37] = (EditText) findViewById(R.id.editText37);
        casillas1[38] = (EditText) findViewById(R.id.editText38);
        casillas1[39] = (EditText) findViewById(R.id.editText39);
        casillas1[40] = (EditText) findViewById(R.id.editText40);
        casillas1[41] = (EditText) findViewById(R.id.editText41);
        casillas1[42] = (EditText) findViewById(R.id.editText42);
        casillas1[43] = (EditText) findViewById(R.id.editText43);
        casillas1[44] = (EditText) findViewById(R.id.editText44);
        casillas1[45] = (EditText) findViewById(R.id.editText45);
        casillas1[46] = (EditText) findViewById(R.id.editText46);
        casillas1[47] = (EditText) findViewById(R.id.editText47);
        casillas1[48] = (EditText) findViewById(R.id.editText48);
        casillas1[49] = (EditText) findViewById(R.id.editText49);
        casillas1[50] = (EditText) findViewById(R.id.editText50);
        casillas1[51] = (EditText) findViewById(R.id.editText51);
        casillas1[52] = (EditText) findViewById(R.id.editText52);
        casillas1[53] = (EditText) findViewById(R.id.editText53);
        casillas1[54] = (EditText) findViewById(R.id.editText54);
        casillas1[55] = (EditText) findViewById(R.id.editText55);
        casillas1[56] = (EditText) findViewById(R.id.editText56);
        casillas1[57] = (EditText) findViewById(R.id.editText57);
        casillas1[58] = (EditText) findViewById(R.id.editText58);
        casillas1[59] = (EditText) findViewById(R.id.editText59);
        casillas1[60] = (EditText) findViewById(R.id.editText60);
        casillas1[61] = (EditText) findViewById(R.id.editText61);
        casillas1[62] = (EditText) findViewById(R.id.editText62);
        casillas1[63] = (EditText) findViewById(R.id.editText63);
        casillas1[64] = (EditText) findViewById(R.id.editText64);
        casillas1[65] = (EditText) findViewById(R.id.editText65);
        casillas1[66] = (EditText) findViewById(R.id.editText66);
        casillas1[67] = (EditText) findViewById(R.id.editText67);
        casillas1[68] = (EditText) findViewById(R.id.editText68);
        casillas1[69] = (EditText) findViewById(R.id.editText69);
        casillas1[70] = (EditText) findViewById(R.id.editText70);
        casillas1[71] = (EditText) findViewById(R.id.editText71);
        casillas1[72] = (EditText) findViewById(R.id.editText72);
        casillas1[73] = (EditText) findViewById(R.id.editText73);
        casillas1[74] = (EditText) findViewById(R.id.editText74);
        casillas1[75] = (EditText) findViewById(R.id.editText75);
        casillas1[76] = (EditText) findViewById(R.id.editText76);
        casillas1[77] = (EditText) findViewById(R.id.editText77);
        casillas1[78] = (EditText) findViewById(R.id.editText78);
        casillas1[79] = (EditText) findViewById(R.id.editText79);
        casillas1[80] = (EditText) findViewById(R.id.editText80);

        casillas2[0] = (EditText) findViewById(R.id.editText100);
        casillas2[1] = (EditText) findViewById(R.id.editText101);
        casillas2[2] = (EditText) findViewById(R.id.editText102);
        casillas2[3] = (EditText) findViewById(R.id.editText103);
        casillas2[4] = (EditText) findViewById(R.id.editText104);
        casillas2[5] = (EditText) findViewById(R.id.editText105);
        casillas2[6] = (EditText) findViewById(R.id.editText106);
        casillas2[7] = (EditText) findViewById(R.id.editText107);
        casillas2[8] = (EditText) findViewById(R.id.editText108);
        casillas2[9] = (EditText) findViewById(R.id.editText109);
        casillas2[10] = (EditText) findViewById(R.id.editText110);
        casillas2[11] = (EditText) findViewById(R.id.editText111);
        casillas2[12] = (EditText) findViewById(R.id.editText112);
        casillas2[13] = (EditText) findViewById(R.id.editText113);
        casillas2[14] = (EditText) findViewById(R.id.editText114);
        casillas2[15] = (EditText) findViewById(R.id.editText115);
        casillas2[16] = (EditText) findViewById(R.id.editText116);
        casillas2[17] = (EditText) findViewById(R.id.editText117);
        casillas2[18] = (EditText) findViewById(R.id.editText118);
        casillas2[19] = (EditText) findViewById(R.id.editText119);
        casillas2[20] = (EditText) findViewById(R.id.editText120);
        casillas2[21] = (EditText) findViewById(R.id.editText121);
        casillas2[22] = (EditText) findViewById(R.id.editText122);
        casillas2[23] = (EditText) findViewById(R.id.editText123);
        casillas2[24] = (EditText) findViewById(R.id.editText124);
        casillas2[25] = (EditText) findViewById(R.id.editText125);
        casillas2[26] = (EditText) findViewById(R.id.editText126);
        casillas2[27] = (EditText) findViewById(R.id.editText127);
        casillas2[28] = (EditText) findViewById(R.id.editText128);
        casillas2[29] = (EditText) findViewById(R.id.editText129);
        casillas2[30] = (EditText) findViewById(R.id.editText130);
        casillas2[31] = (EditText) findViewById(R.id.editText131);
        casillas2[32] = (EditText) findViewById(R.id.editText132);
        casillas2[33] = (EditText) findViewById(R.id.editText133);
        casillas2[34] = (EditText) findViewById(R.id.editText134);
        casillas2[35] = (EditText) findViewById(R.id.editText135);
        casillas2[36] = (EditText) findViewById(R.id.editText136);
        casillas2[37] = (EditText) findViewById(R.id.editText137);
        casillas2[38] = (EditText) findViewById(R.id.editText138);
        casillas2[39] = (EditText) findViewById(R.id.editText139);
        casillas2[40] = (EditText) findViewById(R.id.editText140);
        casillas2[41] = (EditText) findViewById(R.id.editText141);
        casillas2[42] = (EditText) findViewById(R.id.editText142);
        casillas2[43] = (EditText) findViewById(R.id.editText143);
        casillas2[44] = (EditText) findViewById(R.id.editText144);
        casillas2[45] = (EditText) findViewById(R.id.editText145);
        casillas2[46] = (EditText) findViewById(R.id.editText146);
        casillas2[47] = (EditText) findViewById(R.id.editText147);
        casillas2[48] = (EditText) findViewById(R.id.editText148);
        casillas2[49] = (EditText) findViewById(R.id.editText149);
        casillas2[50] = (EditText) findViewById(R.id.editText150);
        casillas2[51] = (EditText) findViewById(R.id.editText151);
        casillas2[52] = (EditText) findViewById(R.id.editText152);
        casillas2[53] = (EditText) findViewById(R.id.editText153);
        casillas2[54] = (EditText) findViewById(R.id.editText154);
        casillas2[55] = (EditText) findViewById(R.id.editText155);
        casillas2[56] = (EditText) findViewById(R.id.editText156);
        casillas2[57] = (EditText) findViewById(R.id.editText157);
        casillas2[58] = (EditText) findViewById(R.id.editText158);
        casillas2[59] = (EditText) findViewById(R.id.editText159);
        casillas2[60] = (EditText) findViewById(R.id.editText160);
        casillas2[61] = (EditText) findViewById(R.id.editText161);
        casillas2[62] = (EditText) findViewById(R.id.editText162);
        casillas2[63] = (EditText) findViewById(R.id.editText163);
        casillas2[64] = (EditText) findViewById(R.id.editText164);
        casillas2[65] = (EditText) findViewById(R.id.editText165);
        casillas2[66] = (EditText) findViewById(R.id.editText166);
        casillas2[67] = (EditText) findViewById(R.id.editText167);
        casillas2[68] = (EditText) findViewById(R.id.editText168);
        casillas2[69] = (EditText) findViewById(R.id.editText169);
        casillas2[70] = (EditText) findViewById(R.id.editText170);
        casillas2[71] = (EditText) findViewById(R.id.editText171);
        casillas2[72] = (EditText) findViewById(R.id.editText172);
        casillas2[73] = (EditText) findViewById(R.id.editText173);
        casillas2[74] = (EditText) findViewById(R.id.editText174);
        casillas2[75] = (EditText) findViewById(R.id.editText175);
        casillas2[76] = (EditText) findViewById(R.id.editText176);
        casillas2[77] = (EditText) findViewById(R.id.editText177);
        casillas2[78] = (EditText) findViewById(R.id.editText178);
        casillas2[79] = (EditText) findViewById(R.id.editText179);
        casillas2[80] = (EditText) findViewById(R.id.editText180);


        for(int i=0;i<casillas1.length;i++){

            String valor_tablero= String.valueOf(cadena[i]);

            if(valor_tablero.equalsIgnoreCase("0")){
                this.casillas1[i].setText("");
                this.casillas2[i].setText("");

            }
            else{
                this.casillas1[i].setText(valor_tablero);
                this.casillas1[i].setFocusable(false);
                this.casillas1[i].setBackgroundColor(Color.GREEN);
                this.casillas2[i].setText(valor_tablero);
                this.casillas2[i].setBackgroundColor(Color.LTGRAY);
            }
        }

        MessageRecoverer messageRecoverer = MessageRecoverer.get(this);
        messageRecoverer.setActivity(this);
        Thread t = new Thread(messageRecoverer);
        t.start();

        for(int i=0;i<casillas1.length;i++) {
            CasillaListener cl = new CasillaListener(casillas1[i],i);
            casillas1[i].addTextChangedListener(cl);
        }


    }

    @Override
    public void showMessage(JSONMessage jsm) {
        if (jsm.getType().equals(SendMovementMessage.class.getSimpleName())) {
            SendMovementMessage smm = (SendMovementMessage) jsm;


            int pos_casilla=smm.getCasilla();
            String valor_casilla=smm.getValor();

            if(valor_casilla.equalsIgnoreCase("")){
                this.casillas2[pos_casilla].setText("");

            }
            else{
                this.casillas2[pos_casilla].setText("X");
            }
        }
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

    public void warning(View view) {
        Intent intent=new Intent(this, WarningActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
