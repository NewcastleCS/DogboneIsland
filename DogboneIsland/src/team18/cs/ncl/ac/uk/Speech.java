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
			Speech=j.getString("speech");
			Speech.replaceAll("{word:ranword}", p.Word);
			Speech.replaceAll("{word:definition}", p.Definition);
			Speech.replaceAll("{word:sampleuse}", p.SampleUse);
			Speech.replaceAll("{word:synonym}", p.Synonym);
			Speech.replaceAll("{character:name}", FbRelatedStuff.Name);
			Speech.replaceAll("{character:lastname}", FbRelatedStuff.LastName);
			Speech.replaceAll("{character:firstname}", FbRelatedStuff.FirstName);
			Speech.replaceAll("{character:pronoun}", pronoun);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
}
