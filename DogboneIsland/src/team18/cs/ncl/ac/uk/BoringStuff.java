package team18.cs.ncl.ac.uk;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import org.xml.sax.SAXException;

import android.app.Application;
import android.os.Environment;

public class BoringStuff extends Application {
	private  String WEB_PATH = "https://raw.github.com/Team18-NewcastleUniversity/resources/master/welcome.txt";
	
	private File ROOT_PATH  = Environment.getExternalStorageDirectory();
	private  File DICTIONARY_PATH = new File(ROOT_PATH," team18.cs.ncl.ac.uk.welcome.json");



	public static LinkedList<String> welcomes =new LinkedList<String>();
	
	public String getRandom()
	{
		try {
		/*
		 * improves start-up time do-not remove
		 */
			if(welcomes.size()<=0) {
				ReadWelcomes();
			}
		}
		catch (Exception e) {
			return "Arrrr!";
		}

		Random rn = new Random();
		if (welcomes.size()==0) {
			return "Arrrrrrr!";
		}
		
		return welcomes.get(rn.nextInt(welcomes.size()));
		
			
	}
	public boolean ParseJSONWelcomes(String json)
	{
		System.out.println("starting parsing");
			String lines[] = json.split("¤");
			for (int i = 0; i < lines.length; i++)
			{
				String added = lines[i];
				welcomes.add(added.substring(0,added.length()-2 ));
			}
		return true;
		
	}
	public boolean ReadWelcomes()
	{
		try {
			String storyJson =ResourceTools.ReadFromLocal(DICTIONARY_PATH);
			ParseJSONWelcomes(storyJson);
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

    public Boolean DownloadWelcomesToLocal()
    {
    	
    	return ResourceTools.DownloadFile(DICTIONARY_PATH, WEB_PATH);

    }
  
}