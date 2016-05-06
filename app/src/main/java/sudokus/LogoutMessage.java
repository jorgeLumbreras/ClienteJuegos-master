package sudokus;

import org.json.JSONException;
import org.json.JSONObject;

import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.JSONable;

public class LogoutMessage extends JSONMessage {
    @JSONable
    private String email;
    @JSONable
    private int idMatch;
    @JSONable
    private boolean pending;

    public LogoutMessage(String email, int idMatch, boolean pending) {
        super(true);
        this.email=email;
        this.idMatch=idMatch;
        this.pending=pending;
    }

    public LogoutMessage(JSONObject jso) throws JSONException {
        this(jso.get("email").toString(),jso.getInt("idMatch"),jso.getBoolean("pending"));
    }

    public String getEmail() {
        return this.email;
    }

    public int getIdMatch(){
        return this.idMatch;
    }
    public boolean getPending(){
        return this.pending;
    }
}
