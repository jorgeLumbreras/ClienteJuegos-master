package edu.uclm.esi.common.jsonMessages;

import org.json.JSONException;
import org.json.JSONObject;

public class LogoutWaitingMessage extends JSONMessage {
    @JSONable
    private String email;

    public LogoutWaitingMessage(String email) {
        super(true);
        this.email=email;
    }

    public LogoutWaitingMessage(JSONObject jso) throws JSONException {
        this(jso.get("email").toString());
    }

    public String getEmail() {
        return this.email;
    }

}
