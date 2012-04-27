package team18.cs.ncl.ac.uk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
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
    TextView WelcomeMsg;
    BoringStuff boringStuff;
    AlertDialog alert ;
    
    
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
         WelcomeMsg=  (TextView) MenuActivity.this.findViewById(R.id.main_welcome);
        firstText =  (TextView) MenuActivity.this.findViewById(R.id.textView1);
        userImage= (ImageView) MenuActivity.this.findViewById(R.id.UserImage);
        
        boringStuff= new BoringStuff();
        boringStuff.ReadWelcomes();
      
        notchify();

        final Button hangyManButton = (Button) findViewById(R.id.hangManbutton);
        final Button flButton = (Button) findViewById(R.id.flashCardButton);
        final Button anagramButton = (Button) findViewById(R.id.anagramButton);
        final Button storyModeButton = (Button) findViewById(R.id.storyModebutton);
        
        /*
         * The interwebz is down....
         * http://g.sevki.org/IBhQS3
         */
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("No internetz");
        builder.setMessage("When your internetz is bubbye, to keep you warm a facepalm will arrive.");
        alert =builder.create();
        
        
        
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
		    if(isOnline())
		    	startActivity(intent);
		    else{
		    	
		    	}
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
     * Lets get a new welcome message.
     */
    @Override
    protected void onResume(){
    	super.onResume();
        notchify();
    }
    
    /*
     * Tell me if I'm online
     */
    public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
    
    /*
     * The point of no return!!!
     * http://g.sevki.org/IknhqS
     */
    @Override
    public void onBackPressed() {
    	notchify();
       return;
    }

	private void notchify() {
		WelcomeMsg.setText(Html.fromHtml("<b>"+boringStuff.getRandom()+"</b>"));
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
