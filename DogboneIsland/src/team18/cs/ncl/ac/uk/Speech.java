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

import java.util.Dictionary;

import org.json.JSONException;
import org.json.JSONObject;

import team18.cs.ncl.ac.uk.FbRelatedStuff.gender;

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
			tryAndReplace(Speech, "\\{word:word\\}", p.Word);
			tryAndReplace(Speech, "\\{word:definition\\}", p.Definition);
			tryAndReplace(Speech, "\\{word:sampleuse\\}", p.SampleUse);
			tryAndReplace(Speech, "\\{word:synonym\\}", p.Synonym);
			tryAndReplace(Speech, "\\{character:name\\}", FbRelatedStuff.Name);
			tryAndReplace(Speech, "\\{[a-zA-z0-9]*\\}", FbRelatedStuff.FirstName);
			tryAndReplace(Speech, "\\{character:lastname\\}", FbRelatedStuff.LastName);
			tryAndReplace(Speech, "\\{character:pronoun\\}", pronoun);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

	private void tryAndReplace(String input, String pattern, String value) {
		// TODO Auto-generated method stub
		try {
			input.replaceAll(pattern, value);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
