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
			JSONObject sea= new JSONObject(DictionaryTools.ReadWebStream(SEARCH_PATH));
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
