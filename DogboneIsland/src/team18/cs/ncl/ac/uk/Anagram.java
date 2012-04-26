// Anagram.java --- 
// 
// Filename: Anagram.java
// Description: 
// Author: Andrew Smith
// Maintainer: Team18
// Created: Tue Apr 24 05:29:38 2012 (+0100)
// Version: 
// Last-Updated: Tue Apr 24 05:30:42 2012 (+0100)
//           By: Sevki Hasirci
//     Update #: 1
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
// 24-Apr-2012    Sevki Hasirci  
//    Last-Updated: Tue Apr 24 05:30:42 2012 (+0100) #1 (Sevki Hasirci)
//    changed the game ending dialog to be handled by GamesCommon for
//    compliance with the story mode.
// 
// 
// 
// 
package team18.cs.ncl.ac.uk;

import java.io.FileNotFoundException;
import java.util.Random;

import team18.cs.ncl.ac.uk.DictionaryTools;
import team18.cs.ncl.ac.uk.WordPair;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class Anagram extends Activity implements OnClickListener
{
	private Button guess, hint;
	public static TextView userGuess, ranWord, lives;
	protected WordPair wordDefPair;
    public static DictionaryTools t = new DictionaryTools();

    
	public static String guessForButton = "";

	public String randomWordFromList;
	public int livesLeft = 3;
	public int ranNum;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anagram);
		setup();
		setWidgetReferences();
		buttonSetup();
	}

	   public void ShowReminder()
	   {
		   GamesCommon.displayWordHint(wordDefPair, this);
			
	   }
	   
	public void setup()
	{
		TextView ranWord = (TextView) findViewById(R.id.wordJumbled);
		TextView lives = (TextView) findViewById(R.id.livesRemaining);

		lives.setText("You have " + livesLeft + " guesses left"); 

		try {
			wordDefPair = getWord();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		randomWordFromList = wordDefPair.Word; //Get the random word from the array.

		String shuffledWord = shuffle(randomWordFromList);

		while(shuffledWord.equals(randomWordFromList))
		{ 
			shuffledWord = shuffle(randomWordFromList);
		}

		shuffledWord = shuffledWord.toLowerCase();
		ranWord.setText(shuffledWord);
	}

	public void buttonSetup()
	{
		Button guess = (Button) findViewById(R.id.Check);  
		Button hint = (Button) findViewById(R.id.hint);  

		guess.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) 
			{
				guessForButton = userGuess.getText().toString();
				play(guessForButton);
			}
		});

		hint.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) 
			{
				ShowReminder();
		
			}
		});

	}


	public static String shuffle(String word)
	{
		char[] FirstWord = word.toCharArray();
		char[] JumbledWord = new char[FirstWord.length];
		for (int i = 0; i < JumbledWord.length; i++) 
		{
			JumbledWord[i]='~';
		}

		for (int i = 0; i < FirstWord.length; i++) 
		{
			int j;

			do
			{
				j=new Random().nextInt(FirstWord.length);
			}
			while( JumbledWord[j] != '~');

			JumbledWord[j]=FirstWord[i];
		}

		String jumbledReturn = "";

		for(int i = 0; i<JumbledWord.length; i++)
		{
			jumbledReturn += JumbledWord[i];
		}
		return jumbledReturn;
	}

	public void play(String word)
	{
		word = word.toLowerCase();
		//ranWord.setText(word); 

		if(word.equals(randomWordFromList.toLowerCase()))
		{
			GamesCommon.displayEndGame(GameStatus.Won, this);
			
		}

		if(!word.equals(randomWordFromList))
		{
			livesLeft--;
			lives.setText("You have " + livesLeft + " guesses left");
		}	

		if(livesLeft == 0)
		{				
			GamesCommon.displayEndGame(GameStatus.Lost, this);
		} 

	}
	
	
	
    public  WordPair getWord() throws FileNotFoundException, InterruptedException
    {
    	
    	WordPair p=	t.getRandom();
    	return p;
    } 	

	public void onClick(View aArg0)
	{
		//				Button aButton = (Button) aArg0;
		//				String A = getString(R.string.A);
		//word.setText("New Test");
	}

	private void setWidgetReferences() 
	{
		ranWord = (TextView) findViewById(R.id.wordJumbled);
		guess = (Button) findViewById(R.id.Check);
		hint = (Button) findViewById(R.id.hint);
		userGuess = (TextView) findViewById(R.id.wordGuess);
		lives = (TextView) findViewById(R.id.livesRemaining);
	}


}