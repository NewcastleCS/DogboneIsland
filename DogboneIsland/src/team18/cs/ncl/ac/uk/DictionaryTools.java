// DictionaryTools.java --- 
// 
// Filename: DictionaryTools.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Mon Apr 16 22:50:57 2012 (+0100)
// Version: 
// Last-Updated: Sat Apr 21 19:56:02 2012 (+0100)
//           By: Sevki Hasirci
//     Update #: 3
// URL: http://sevki.org
// Keywords: 
// Compatibility: 
// 
// 

// Commentary: 
// 
// 
// 
// 

// Change Log:
// 21-Apr-2012    Sevki Hasirci  
//    Last-Updated: Sat Apr 21 19:56:02 2012 (+0100) #3 (Sevki Hasirci)
//    Added Download the story.json----
//	  Removed the download tools and moved them to ResourceTools.java
// 16-Apr-2012    Sevki Hasirci  
//    Last-Updated: Mon Apr 16 22:51:42 2012 (+0100) #2 (Sevki Hasirci)
//    Moved the Dictionary file from github to ncl.sevki.org. it is now generated dynamically at ncl.sevki.org/dictionaryJ.php
// 
// 
// 
// 
package team18.cs.ncl.ac.uk;

import org.json.JSONException;
import org.json.JSONObject;	
import org.json.JSONArray;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Random;
import org.xml.sax.SAXException;

import android.R.xml;
import android.app.Application;
import android.os.Environment;

public class DictionaryTools extends Application {
	private  String WEB_PATH = "http://ncl.sevki.org/dictionaryJ.php";
	
	private File ROOT_PATH  = Environment.getExternalStorageDirectory();
	private  File DICTIONARY_PATH = new File(ROOT_PATH," team18.cs.ncl.ac.uk.dictionary.json");



	public static LinkedList<WordPair> pairs = new LinkedList<WordPair>();
	
	public WordPair getRandom()
	{
		try {
			System.out.println("reading");
			ReadDictionary();
		} catch (Exception e) {
		}
		System.out.println("getting random");
		Random rn = new Random();
		if (pairs.size()==0) {
			return new WordPair("ERROR", "ERROR");
		}
		return pairs.get(rn.nextInt(pairs.size()));
		
			
	}
	public boolean ParseJSONDictionary(String json)
	{
		System.out.println("starting parsing");
			try {
				JSONObject job = new JSONObject(json);
				JSONArray ja = job.getJSONArray("dictionary");
				for (int i = 0; i < ja.length(); i++)
				{
					WordPair p = new WordPair();
					
					JSONObject jo = ja.getJSONObject(i);
					p.Definition=jo.getString("definition");
					byte[] a=jo.getString("word").getBytes("UTF-8");
					p.Word=new String(a,"UTF-8" );
					p.Synonym=jo.getString("synonym");
					p.SampleUse=jo.getString("sample_use");
					p.ImagePath=jo.getString("image_path");
					p.Difficulty=jo.getInt("difficulty");
					p.WordId=jo.getInt("word_id");
					pairs.add(p);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
			
				e.printStackTrace();
				return false;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
		
	}
	public boolean ReadDictionary()
	{
		try {
			String storyJson =ResourceTools.ReadFromLocal(DICTIONARY_PATH);
			ParseJSONDictionary(storyJson);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

    public Boolean DownloadDictionaryToLocal()
    {
    	
    	return ResourceTools.DownloadFile(DICTIONARY_PATH, WEB_PATH);

    }
  
}
