package team18.cs.ncl.ac.uk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.android.AsyncFacebookRunner;

public class MenuActivity extends Activity {
    /** Called when the activity is first created. */
    
    
    public  AsyncFacebookRunner mAsyncRunner=new AsyncFacebookRunner(FbRelatedStuff.facebook);
    private static TextView firstText;
    private static ImageView userImage;
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        

        firstText =  (TextView) MenuActivity.this.findViewById(R.id.textView1);
        userImage= (ImageView) MenuActivity.this.findViewById(R.id.UserImage);
        firstText.setText("Arrr! Welcome to t' Dogbone Island!!!");
        //buttons and shit

        final Button hangyManButton = (Button) findViewById(R.id.hangManbutton);
        final Button flButton = (Button) findViewById(R.id.flashCardButton);
        final Button anagramButton = (Button) findViewById(R.id.anagramButton);
        final Button storyModeButton = (Button) findViewById(R.id.storyModebutton);
         
        
        
        hangyManButton.setOnClickListener(new View.OnClickListener() {
     		@Override
		    public void onClick(View v) {
		    Intent intent =
			new Intent(team18.cs.ncl.ac.uk.MenuActivity.this, team18.cs.ncl.ac.uk.HangManActivity.class);
		    startActivity(intent);
		    
		}
	    });
        //Jamie this is your bitch
        flButton.setOnClickListener(new View.OnClickListener() {
     		@Override
		    public void onClick(View v) {
		    Intent intent =
			new Intent(team18.cs.ncl.ac.uk.MenuActivity.this, team18.cs.ncl.ac.uk.FlashCards.class);
		    startActivity(intent);
		}
	    });
        
	//Andy this is your bitch
        anagramButton.setOnClickListener(new View.OnClickListener() {
     		@Override
		    public void onClick(View v) {
		    // TODO Auto-generated method stub
		    Intent intent =
			new Intent(team18.cs.ncl.ac.uk.MenuActivity.this, team18.cs.ncl.ac.uk.Anagram.class);
		    startActivity(intent);
		}
	    });   
        storyModeButton.setOnClickListener(new View.OnClickListener() {
     		@Override
		    public void onClick(View v) {
		    StoryTools.ReadStory();
		    Intent intent =
			new Intent(team18.cs.ncl.ac.uk.MenuActivity.this, team18.cs.ncl.ac.uk.StoryActivity.class);
		    startActivity(intent);
     		}
     		});
        
        

		
		MenuActivity.this.runOnUiThread(new Runnable() {
			public void run() {
			    firstText.setText("Ahoy there " + FbRelatedStuff.FirstName + "!");
			    getImage(FbRelatedStuff.ImagePath);
			}
		    }
		);
		
        
        
        }
    
    
    
    /*
     * The point of no return!!!
     * http://g.sevki.org/IknhqS
     */
    @Override
    public void onBackPressed() {
       return;
    }
    
    /*
     * Image Puller Start HERE.   HOLDON!!!!!!!!
     */
    void getImage(final String S)
    {
    	MenuActivity.this.runOnUiThread(new Runnable() {
		public void run() {
		    new GetProfilePicAsyncTask().execute(S);
		}
	    });

	
    }
    
    /*
     * REALLY IMPORTANT STUFF
     */
    private class GetProfilePicAsyncTask extends AsyncTask<Object, Void, Bitmap>  
    {
	String picturePath;
	
        @Override
	    protected Bitmap doInBackground(Object... params) {
	    picturePath =  params[0].toString();
	    return  Utility.getBitmap(picturePath);
	    
            
        }
	
        @Override
	    protected void onPostExecute(final Bitmap result) {
            
	    MenuActivity.this.runOnUiThread(new Runnable() {
        	    public void run() {
			userImage.setImageBitmap(result);
			
        	    }
		});
        }
    }

}
