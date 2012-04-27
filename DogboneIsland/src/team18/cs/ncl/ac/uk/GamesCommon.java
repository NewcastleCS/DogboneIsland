// GamesCommon.java --- 
// 
// Filename: GamesCommon.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Tue Apr 24 05:34:19 2012 (+0100)
// Version: 
// Last-Updated: Fri Apr 27 16:00:13 2012 (+0100)
//           By: Sevki Hasirci
//     Update #: 1
// URL: http://sevki.org
// Keywords: 
// Compatibility: 
// 
// 

// Commentary: 
// This class is here to ensure games all have a common ending and hint dialog.
// 
// 
// 

// Change Log:
// 27-Apr-2012    Sevki Hasirci  
//    Last-Updated: Fri Apr 27 16:00:13 2012 (+0100) #1 (Sevki Hasirci)
//    Added the facebook bindings here asswell, now it will post game win or 
//    defeats to facebook for everygame within the same method.
// 
// 
// 
package team18.cs.ncl.ac.uk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GamesCommon {

	public static void displayEndGame(GameStatus gs,final Activity li,WordPair p) {
		
		//New intent 
	
		Bundle extras = li.getIntent().getExtras(); 
		int NextChap = -1;
	
		
		
			Dialog dialog = new Dialog(li);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.game_end);
		
		
		TextView endCaption = (TextView) dialog.findViewById(R.id.game_end_caption);
		Button button1 = (Button) dialog.findViewById(R.id.game_end_button1);
		Button button2 = (Button) dialog.findViewById(R.id.game_end_button2);
		ImageView finsihImage = (ImageView) dialog.findViewById(R.id.game_end_finishimage);
		
		if(gs== GameStatus.Won)
		{
			endCaption.setText((li.getString(R.string.WonTxt)));
			finsihImage.setVisibility(0);
			}
		else if(gs== GameStatus.Lost)
		{
			endCaption.setText((li.getString(R.string.LostTxt)));
			finsihImage.setVisibility(8);
		}
		
		
		if(extras !=null)
		{
			button2.setVisibility(8);
		}
		else 
		{
			button1.setText("New Game");
			button1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					final Intent intent = new Intent(li,li.getClass());
					li.finish();
					li.startActivity(intent);
				}
			});
			button2.setText("Main menu");
			button2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					final Intent intent = new Intent(li,DogboneIslandActivity.class);
					li.finish();
					li.startActivity(intent);
				}
			});
		}
		
		if(extras !=null && gs == GameStatus.Won)
		{
			int a = extras.getInt("nextChapter");
			final Intent intent = new Intent(li,StoryActivity.class);
			Bundle b = new Bundle();
			b.putInt("chapter", a);
			intent.putExtras(b);
			button1.setText("Continue");
			button1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					li.finish();
					li.startActivity(intent);
				}
			});
		}
		else if(extras !=null && gs == GameStatus.Lost)
		{
			NextChap = extras.getInt("nextChapter");
			final Intent intent = new Intent(li,li.getClass());
			Bundle b = new Bundle();
			b.putInt("nextChapter", NextChap); 
			intent.putExtras(b); 
			button1.setText("Try again");
			button1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					li.finish();
					li.startActivity(intent);
				}
			});
		}
		
int gameInt =1;
	if(li.getClass() == HangManActivity.class)
	{
		gameInt = 1;
	}else if (li.getClass() == FlashCards.class)
	{
		gameInt = 2;
	}else if(li.getClass() == Anagram.class)
	{
		gameInt =3;
	}
	int won = 0;
	switch (gs) {
	case Won:
		won =1;
		break;
	case Lost:
		won = -1;
		break;

	default:
		break;
	}
	
	
	    DogBoneServer.sendUserScoreJson(FbRelatedStuff.uid,gameInt,p.WordId, won,-1);
		dialog.show();
		
	    }
public static void displayWordHint(WordPair p,final Activity li) {
		
		//New intent 
			 
		final Dialog dialog = new Dialog(li);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.word_hint);
		
		LinearLayout mother = (LinearLayout) dialog.findViewById(R.id.word_hint_mother);
		TextView Definition = (TextView) dialog.findViewById(R.id.word_hint_definition);
		TextView Synonym = (TextView) dialog.findViewById(R.id.word_hint_synonym);
		TextView SampleUse = (TextView) dialog.findViewById(R.id.word_hint_sample_use);
		
		dialog.setCancelable(true);
		Definition.setText(Html.fromHtml("<b>Definition:</b>"+p.Definition));
		Synonym.setText(p.Synonym);
		SampleUse.setText(Html.fromHtml("<b>Sample use:</b>"+p.SampleUse));
		
		dialog.show();
		
		
		/*
		 * Lets set it up so when clicked on this control disappears...
		 */
		dialog.setCanceledOnTouchOutside(true);
		mother.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		


	    }

}
