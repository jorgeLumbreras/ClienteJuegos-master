package edu.uclm.esi.common.jsonMessages;

import org.json.JSONException;
import org.json.JSONObject;

import sudokus.DefeatAnnouncement;
import sudokus.ListRecordsAnnouncement;
import sudokus.SendMovementMessage;
import sudokus.SudokuBoardMessage;
import sudokus.SurrenderAnnouncement;
import sudokus.VictoryAnnouncement;

public class JSONMessagesBuilder {
	public static JSONMessage build(JSONObject jso) throws JSONException {
		if (jso.get("type").equals(ErrorMessage.class.getSimpleName()))
			return new ErrorMessage(jso);
		if (jso.get("type").equals(LoginMessage.class.getSimpleName()))
			return new LoginMessage(jso);
		if (jso.get("type").equals(LoginMessageAnnouncement.class.getSimpleName()))
			return new LoginMessageAnnouncement(jso);
		if (jso.get("type").equals(LogoutMessageAnnouncement.class.getSimpleName()))
			return new LogoutMessageAnnouncement(jso);
		if (jso.get("type").equals(OKMessage.class.getSimpleName()))
			return new OKMessage(jso);
		if (jso.get("type").equals(RegisterMessage.class.getSimpleName()))
			return new RegisterMessage(jso);
		if (jso.get("type").equals(MessageList.class.getSimpleName()))
			return new MessageList(jso);
		if (jso.get("type").equals(SudokuBoardMessage.class.getSimpleName()))
			return new SudokuBoardMessage(jso);
		if (jso.get("type").equals(SendMovementMessage.class.getSimpleName()))
			return new SendMovementMessage(jso);
		if (jso.get("type").equals(SurrenderAnnouncement.class.getSimpleName()))
			return new SurrenderAnnouncement(jso);
		if (jso.get("type").equals(VictoryAnnouncement.class.getSimpleName()))
			return new VictoryAnnouncement(jso);
		if (jso.get("type").equals(DefeatAnnouncement.class.getSimpleName()))
			return new DefeatAnnouncement(jso);
		if (jso.get("type").equals(ListRecordsAnnouncement.class.getSimpleName()))
			return new ListRecordsAnnouncement(jso);




/*
		if (jso.get("type").equals(SudokuBoardMessage.class.getSimpleName()))
			return new SudokuBoardMessage(jso);
		if (jso.get("type").equals(SudokuBoardMessage.class.getSimpleName()))
			return new SudokuBoardMessage(jso);
*/
		return null;
	}
}
