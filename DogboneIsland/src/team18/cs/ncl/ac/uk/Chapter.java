// Chapter.java --- 
// 
// Filename: Chapter.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Sun Apr 22 19:30:06 2012 (+0100)
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
// Handles chapters from the story
// 
// 
// 

// Change Log:
// 
// 
// 
// 
package team18.cs.ncl.ac.uk;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Chapter {
    public int id;
    public String Name;
    public int SetIn;
    public LinkedList<GameChar> Characters = new LinkedList<GameChar>();
    public LinkedList<Speech> Dialog =new LinkedList<Speech>();
    public Iterator<Speech> SpeechIterator;
    public int NextAction;
    public int NextActionParams;
		
    /** 
     * Takes in  a JSON strong and parses its values.
     */ 
    Chapter(String json)
    {
	JSONObject thisChapter;
	try {
	    thisChapter = new JSONObject(json);
	    id =thisChapter.getInt("id");
	    Name = thisChapter.getString("name");
	    SetIn= thisChapter.getInt("SetIn");
	    JSONArray chars = thisChapter.getJSONArray("characters");
	    JSONArray dialog = thisChapter.getJSONArray("dialog");
						
	    for(int i =0; i<chars.length();i++)
		{
		    Characters.add(new GameChar(chars.get(i).toString()));
		}
	    for(int i =0; i<dialog.length();i++)
		{
		    Dialog.add(new Speech(dialog.get(i).toString()));
		}
	    NextAction = thisChapter.getInt("nextAction");
	    NextActionParams = thisChapter.getInt("nextActionParams");
						
	} catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
			
				
	SpeechIterator=Dialog.iterator();
			
    }
}
