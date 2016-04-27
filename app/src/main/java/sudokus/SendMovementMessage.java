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
    @JSONable
    private String board;
    @JSONable
    private int time;

    public SendMovementMessage(String user, int idMatch, int casilla, String valor, String board, int time) {
        super(true);
        this.user = user;
        this.casilla = casilla;
        this.valor = valor;
        this.idMatch = idMatch;
        this.board = board;
        this.time=time;
    }

    public SendMovementMessage(JSONObject jso) throws JSONException {
        this(jso.getString("user"),jso.getInt("idMatch"),jso.getInt("casilla"),jso.getString("valor"),jso.getString("board"),jso.getInt("time"));
    }

    public String getUser() { return this.user; }

    public int getCasilla() { return this.casilla; }

    public int getIdMatch() { return this.idMatch; }

    public String getValor() {
        return this.valor;
    }

    public String getBoard() { return this.board; }

    public int getTime() { return this.time; }
}
