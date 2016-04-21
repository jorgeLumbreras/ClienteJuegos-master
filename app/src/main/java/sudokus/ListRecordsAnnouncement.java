package sudokus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uclm.esi.common.jsonMessages.JSONMessage;
import edu.uclm.esi.common.jsonMessages.JSONable;

public class ListRecordsAnnouncement extends JSONMessage {

	 @JSONable
	 private JSONArray records; 

	
	public ListRecordsAnnouncement(JSONArray records) {
		super(true);
		this.records=records;
		}
		

    public ListRecordsAnnouncement(JSONObject jso) throws JSONException {
        this(jso.getJSONArray("records"));
    }

    public JSONArray getRecords() { return this.records; }

}
