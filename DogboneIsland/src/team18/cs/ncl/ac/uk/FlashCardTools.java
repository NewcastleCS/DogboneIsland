// FlashCardTools.java --- 
// 
// Filename: FlashCardTools.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Fri Apr 27 15:58:00 2012 (+0100)
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
// this class googles the internetz with bing for images of certain words 
// of the dictionary.
// 
// 

// Change Log:
// 
// 
// 
// 
package team18.cs.ncl.ac.uk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Application;

public class FlashCardTools extends Application {
	
	

	public static String getImageUrlForWord(String p)
	{
		String SEARCH_PATH = "http://api.bing.net/json.aspx?AppID=B8E39C5C43D0A494048CED221914969B3893FD4E&Sources=Image&Image.Count=1&Query=";
		
		SEARCH_PATH +=p;
		try {
			JSONObject sea= new JSONObject(ResourceTools.ReadWebStream(SEARCH_PATH));
			JSONObject sr= (JSONObject) sea.get("SearchResponse");
			JSONObject img= (JSONObject) sr.get("Image");
			JSONArray rs= (JSONArray) img.get("Results");
			JSONObject r = (JSONObject) rs.get(0);
			JSONObject t= (JSONObject) r.get("Thumbnail");
			return t.getString("Url");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			return "bkb";
		}
			
	}
	

}
