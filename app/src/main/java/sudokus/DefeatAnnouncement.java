package sudokus;

import org.json.JSONException;
import org.json.JSONObject;

import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.JSONable;

public class DefeatAnnouncement extends JSONMessage{
	
	@JSONable
    private String mensaje;

    public DefeatAnnouncement(String mensaje) {
        super(true);
        this.mensaje = mensaje;
        
    }

    public DefeatAnnouncement(JSONObject jso) throws JSONException {
        this(jso.getString("mensaje"));
    }

    public String getMensaje() { return this.mensaje; }
	
}
