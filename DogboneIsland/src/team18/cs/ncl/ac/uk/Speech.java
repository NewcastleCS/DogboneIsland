// Speech.java --- 
// 
// Filename: Speech.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Sun Apr 22 19:31:21 2012 (+0100)
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
// Speech handlers for the story
// {word:word} {word:definition} {word:sampleuse} {word:synonym}
// {character:name}{character:lastname}{character:firstname}{character:pronoun}
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

public class Speech {

	public int From;
	public String Speech;
	
	public Speech(String json)
	{
		try {
			String pronoun = "he";
			
			switch (FbRelatedStuff.Gender) {
			case male:
				pronoun = "he";
				break;
			case female:
				pronoun = "she";
				break;

			default:
				break;
			}
			
			DictionaryTools t =new DictionaryTools();
			WordPair p = t.getRandom();
			JSONObject j = new JSONObject(json);
			From = j.getInt("from");
			Speech=j.getString("text");
			Speech =tryAndReplace(Speech, "\u007Bword\u003Aword\u007D", p.Word);
			Speech =tryAndReplace(Speech, "\u007Bword\u003Adefinition\u007D", p.Definition);
			Speech =tryAndReplace(Speech, "\u007Bword\u003Asampleuse\u007D", p.SampleUse);
			Speech =tryAndReplace(Speech, "\u007Bword\u003Asynonym\u007D", p.Synonym);
			Speech =tryAndReplace(Speech, "\u007Bcharacter\u003Aname\u007D", FbRelatedStuff.Name);
			Speech =tryAndReplace(Speech, "\u007Bcharacter\u003Afirstname\u007D", FbRelatedStuff.FirstName);
			Speech =tryAndReplace(Speech, "\u007Bcharacter\u003Alastname\u007D", FbRelatedStuff.LastName);
			Speech =tryAndReplace(Speech, "\u007Bcharacter\u003Apronoun\u007D", pronoun);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

	private String tryAndReplace(String input, String pattern, String value) {
		// TODO Auto-generated method stub
		try {
			return input.replace(pattern, value);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return input;
		}
	}
}
