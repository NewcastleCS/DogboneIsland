package team18.cs.ncl.ac.uk;

public class WordPair {
	public int WordId;
public String Word;
public String Definition;
public String Synonym;
public String SampleUse;
public String ImagePath;
public int Difficulty;	

	WordPair(int id, String word, String definition, String synonym, String sampleuse, String imgpath, int diff)
	{
		WordId = id;
		Word =word;
		Definition = definition;
		Synonym = synonym;
		SampleUse = sampleuse;
		ImagePath=imgpath;
		Difficulty= diff;
	}
	WordPair(String s, String d)
	{
		WordId = -1;
		Word ="ERROR";
		Definition = "ERROR";
		Synonym = "ERROR";
		SampleUse = "ERROR";
		ImagePath="ERROR";
		Difficulty= 0;
	}
	public WordPair() {
		// TODO Auto-generated constructor stub
		
	}
}
