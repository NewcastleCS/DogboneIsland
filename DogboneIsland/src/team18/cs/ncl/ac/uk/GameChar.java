// GameChar.java --- 
// 
// Filename: GameChar.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Fri Apr 27 15:59:28 2012 (+0100)
// Version: 
// Last-Updated: 
//           By: 
//     Update #: 0
// URL: http://sevki.org
// Keywords: 
// Compatibility: 
// 
// 

// Commentary: 
// Parses GameCharacthers in to objects.
// 
// 
// 

// Change Log:
// 
// 
// 
// 
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
				CharPic = thisChar.getInt("CharacterImage");
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
