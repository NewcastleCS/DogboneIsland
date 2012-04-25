// StoryActivity.java --- 
// 
// Filename: StoryActivity.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Mon Apr 23 18:53:11 2012 (+0100)
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
// 
// 
// 
// 

// Change Log:
// 
// 
// 
// 
package team18.cs.ncl.ac.uk;

import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StoryActivity extends Activity{
    
	
    // on screen images
    public ImageView protagonistImage;
    public ImageView antagonistImage;
    
    // text
    public TextView dialogText;
    // story canvas
    public RelativeLayout canvas;
    
    // Current chapter That im playing
    Chapter currChap;
    //What am I talking about
    Iterator<Speech>  talkingPoints;

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);     
	setContentView(R.layout.story);
	
	//find controls and set them.
	protagonistImage = (ImageView) findViewById(R.id.Protagonist);
	antagonistImage = (ImageView) findViewById(R.id.Protagonist);
	dialogText = (TextView) findViewById(R.id.DialogText);
	canvas = (RelativeLayout) findViewById(R.id.storyCanvas);
	
// Set up Chapter
	Bundle extras = getIntent().getExtras(); 
	int thisChap = -1;
	if(extras !=null)
	{
		thisChap = extras.getInt("chapter");
		currChap  = StoryTools.chapters.get(thisChap);
	}
	else
	{
		currChap  = StoryTools.chapters.getFirst();
	}
	SetChars();
	SetupDialogController();
	talkingPoints=currChap.Dialog.iterator();

	//setup bg
	canvas.setBackgroundResource(currChap.SetIn);
	//say the first thing...
	SayNext();
	
	
	
    }
    /*
     * Dialog setup and progress keeper
     */
    private void SetupDialogController() {
		// TODO Auto-generated method stub
    	canvas.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				SayNext();
				return false;
			}

	
		});
	}
	private void SayNext() {
		// TODO Auto-generated method stub
		if(talkingPoints.hasNext())
			dialogText.setText(talkingPoints.next().Speech);
		else
		{
			Intent intent;
				switch (currChap.NextAction) {
			case 1:
				intent= new Intent(StoryActivity.this, HangManActivity.class);
				break;
			case 2:
				intent= new Intent(StoryActivity.this, FlashCards.class);
				break;
			case 3:
				intent= new Intent(StoryActivity.this, Anagram.class);
				break;
			case 4:
				intent= new Intent(StoryActivity.this, DogboneIslandActivity.class);
				break;

			default:
				intent= new Intent(StoryActivity.this, DogboneIslandActivity.class);
				break;
			}
				Bundle b = new Bundle();
			b.putInt("nextChapter",currChap.NextActionParams); //Your id
			intent.putExtras(b); //Put your id to your next Intent
			startActivity(intent);
			finish();
		
		}
	}
	public void SetChars()
    {
	GameChar protagonist =	currChap.Characters.getFirst();
	GameChar antagonist  =	currChap.Characters.getLast();
	System.out.println(protagonist.CharPic + "  asd" + antagonist.CharPic);
	protagonistImage.setImageResource(protagonist.CharPic);
	antagonistImage.setImageResource(antagonist.CharPic);
	
    }
  
}