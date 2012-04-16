package team18.cs.ncl.ac.uk;

import java.io.BufferedReader;

import org.json.JSONException;
import org.json.JSONObject;	
import org.json.JSONArray;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.app.Application;
import android.os.Environment;

public class DictionaryTools extends Application {
	private  String WEB_PATH = "http://ncl.sevki.org/dictionaryJ.php";
	
	private File ROOT_PATH  = Environment.getExternalStorageDirectory();
	private  File DICTIONARY_PATH = new File(ROOT_PATH," team18.cs.ncl.ac.uk.dictionary.json");


	boolean inWord = false;
	boolean inDef = false;
	public static LinkedList<WordPair> pairs = new LinkedList<WordPair>();
	class MyXmlContentHandler extends DefaultHandler {
		String currentNode;
		  @Override
	        public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes atts) throws SAXException {
	            super.startElement(uri, localName, qName, atts);
	            if (localName.equals("item")) {
	                new WordPair();
	            	pairs.add(new WordPair());
	            } 
	            
	            inWord=localName.equals("word") ? true:false;
	            inDef = localName.equals("definition") ? true:false;
	        }
		  @Override
		  public void characters(char[] ch, int start, int length) throws SAXException {

		      String value = new String(ch, start, length);
		      if (inDef &&pairs.size()>0){
		    	
		    	  pairs.get(pairs.size()-1).Definition += value;
		    	  String s =pairs.get(pairs.size()-1).Definition;
			      System.out.println("definition:"+value+"->"+s);
			      pairs.get(pairs.size()-1).Definition=s.trim();
			  	
		      }else if (inWord&&pairs.size()>0){
		    	
			    	  pairs.get(pairs.size()-1).Word += value.toString();
			    	  String s =pairs.get(pairs.size()-1).Word;
				      System.out.println("word:"+value +"->"+s);
				      pairs.get(pairs.size()-1).Word=s.trim();
				  	
			      }
		  
		  }

		}
	public WordPair getRandom()
	{
		try {
			System.out.println("reading");
			ReadFromLocal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("io barfed");
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("SAX has dierrhieaaaaaa");
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					p.Word=jo.getString("word");
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
			}
		return true;
		
	}
	public boolean ReadFromLocal() throws  IOException, SAXException
	{
		        FileInputStream fIn;
		        System.out.println("now opening");
	         fIn = new FileInputStream(DICTIONARY_PATH);
	         StringBuffer sBuffer = new StringBuffer("");
	         int ch;
	         System.out.println("now reading");
	         while( (ch = fIn.read()) != -1)
	             sBuffer.append((char)ch);
	         
	         System.out.println("now parsing" + sBuffer +"wrote sbuffer");
	         // Deprecated, switched to JSON Instead of XML.
	         // Xml.parse(sBuffer.toString(), new MyXmlContentHandler());
	         
	         ParseJSONDictionary(sBuffer.toString());
	         return false;
	}
    public Boolean DownloadDictionaryToLocal()
    {
    	String reString = ReadWebStream(WEB_PATH);
    	 try {
				File f = DICTIONARY_PATH;
			
				if (!f.exists()) {
					f.createNewFile();
				}
				System.out.print("about to write"+reString);
			FileOutputStream fos = new FileOutputStream(DICTIONARY_PATH);
			fos.write(reString.getBytes());
			System.out.print("wrote"+reString);

			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		catch (NullPointerException e) {
		
		}
		return true;

    }
    public static String ReadWebStream(String url) {
    	StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}
