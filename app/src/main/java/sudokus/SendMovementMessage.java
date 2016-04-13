package sudokus;

import org.json.JSONException;
import org.json.JSONObject;

import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.JSONable;

/**
 * Created by Jaime on 12/04/2016.
 */
public class SendMovementMessage extends JSONMessage {

    @JSONable
    private String user;
    @JSONable
    private int idMatch;
    @JSONable
    private int casilla;
    @JSONable
    private String valor;

    public SendMovementMessage(String user, int idMatch, int casilla, String valor) {
        super(true);
        this.user = user;
        this.idMatch = idMatch;
        this.casilla = casilla;
        this.valor = valor;
    }

    public SendMovementMessage(JSONObject jso) throws JSONException {
        this(jso.getString("user"),jso.getInt("idMatch"),jso.getInt("casilla"),jso.getString("valor"));
    }

    public String getUser() { return this.user; }

    public int getIdMatch() { return this.idMatch; }

    public int getCasilla() { return this.casilla; }

    public String getValor() {
        return this.valor;
    }
}
