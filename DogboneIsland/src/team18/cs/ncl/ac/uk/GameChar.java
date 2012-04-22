package team18.cs.ncl.ac.uk;

import org.json.JSONException;
import org.json.JSONObject;

public class GameChar {
	public GameChar(String json) {
		
		JSONObject thisChar;
			try {
				thisChar=new JSONObject(json);
				CharID = thisChar.getInt("CharID");
				CharName= thisChar.getString("CharacterName");
				
			}
				catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}
	public int CharID;
	public String CharName;
	public int CharPic;
}
