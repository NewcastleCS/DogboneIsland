package team18.cs.ncl.ac.uk;

import java.net.URL;


import android.R.string;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class FlashCards extends Activity{
	public ImageView x;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x=(ImageView)findViewById(R.id.flashcardMainImageView);
       DictionaryTools t = new DictionaryTools();
       WordPair p =t.getRandom();
      String urlOfWString =FlashCardTools.getImageUrlForWord(p.Word);
      getImage(urlOfWString);
        setContentView(R.layout.flashcards);
    }
    void getImage(final String S)
    {
    	FlashCards.this.runOnUiThread(new Runnable() {
		public void run() {
		    new GetFlashCardPicAsyncTask().execute(S);
		}
	    });

	
    }
	 private class GetFlashCardPicAsyncTask extends AsyncTask<Object, Void, Bitmap>  
	    {
		String picturePath;
		
	        @Override
		    protected Bitmap doInBackground(Object... params) {
		    picturePath =  params[0].toString();
		    return  Utility.getBitmap(picturePath);
		    
	            
	        }
		
	        @Override
		    protected void onPostExecute(final Bitmap result) {
	            
		    FlashCards.this.runOnUiThread(new Runnable() {
	        	    public void run() {
			if(x != null)
			x.setImageBitmap(result);
			else
			{
				 x=(ImageView)findViewById(R.id.flashcardMainImageView);
					x.setImageBitmap(result);
						 
			}
	        	    }
			});
	        }
	    }
}
