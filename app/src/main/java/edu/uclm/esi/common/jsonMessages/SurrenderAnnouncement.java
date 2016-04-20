package edu.uclm.esi.common.jsonMessages;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JorgeLumbreras on 19/4/16.
 */
public class SurrenderAnnouncement extends JSONMessage {
    @JSONable
    private String email;

    @JSONable
    private int idMatch;

    public SurrenderAnnouncement(String email, int idMatch) {
        super(true);
        this.email=email;
        this.idMatch=idMatch;
    }

    public SurrenderAnnouncement(JSONObject jso) throws JSONException {
        this(jso.get("email").toString(),Integer.parseInt(jso.get("idMatch").toString()));

    }

    public String getEmail() {
        return this.email;
    }
    public int getIdMatch() {
        return this.idMatch;
    }}
