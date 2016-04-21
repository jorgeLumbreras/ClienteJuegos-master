package sudokus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.JSONable;

/**
 * Created by Jaime on 21/04/2016.
 */
public class GetRecordsMessage extends JSONMessage {
    @JSONable
    private String text;

    public GetRecordsMessage(String text) {
        super(true);
        this.text=text;
    }

    public GetRecordsMessage(JSONObject jso) throws JSONException {
        this(jso.get("records").toString());
    }

    public String getText() { return this.text; }
}
