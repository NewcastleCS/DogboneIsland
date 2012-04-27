// DogboneIslandActivity.java --- 
// 
// Filename: DogboneIslandActivity.java
// Description: 
// Author: Sevki Hasirci
// Maintainer: Sevki Hasirci
// Created: Wed Apr  4 13:50:40 2012 (+0100)
// Version: 
// Last-Updated: Fri Apr 27 01:33:45 2012 (+0100)
//           By: Sevki Hasirci
//     Update #: 1
// URL: http://sevki.com
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
// 27-Apr-2012    Sevki Hasirci  
//    Last-Updated: Fri Apr 27 01:33:45 2012 (+0100) #1 (Sevki Hasirci)
//    Separated the Facebook login and the new menu, now when clicked back, game doesn't go back to the facebook login.
// 
// 
// 
// 
package team18.cs.ncl.ac.uk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import com.facebook.android.*;
import com.facebook.android.Facebook.*;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import team18.cs.ncl.ac.uk.SessionEvents.AuthListener;



public class DogboneIslandActivity extends Activity {
    /** Called when the activity is first created. */
    
    
    public  AsyncFacebookRunner mAsyncRunner=new AsyncFacebookRunner(FbRelatedStuff.facebook);
    String FILENAME = "AndroidSSO_data";
    private static TextView firstText;
    private static Boolean gotFbData = false;
    private static Boolean gotImagePath = false;
    private SharedPreferences mPrefs;
    private ProgressDialog pdialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * http://g.sevki.org/Ilj8BO
         */
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_welcome);
	
        /*
         * Progressbar
         */
        pdialog = ProgressDialog.show(this, "", 
                "We are talking to the pirates our pirate mateys!! Be patient you scurvy dog...", true);
    
    	
        mPrefs = getSharedPreferences(FILENAME,MODE_PRIVATE);
        String access_token = mPrefs.getString("access_token", null);
        long expires = mPrefs.getLong("access_expires", 0);
        
        
  
        if(access_token != null) {
            FbRelatedStuff.facebook.setAccessToken(access_token);
            
        }
        if(expires != 0) {
	    FbRelatedStuff.facebook.setAccessExpires(expires);
        }
        
        
        System.out.print("create dictionary and download");
        DictionaryTools t = new DictionaryTools();
        t.DownloadDictionaryToLocal();
        System.out.print("create story and download");
        StoryTools st = new StoryTools();
        st.DownloadStoryToLocal();
        

    
        //Facebook Stuff
        FbRelatedStuff.facebook.authorize(this, new String[] { 
		"user_photos",
		"user_about_me",
		"publish_actions",
		"offline_access",
		"user_games_activity",
		"friends_games_activity",
		"user_actions:newcaslteproject",
		"friends_actions:newcaslteproject" },
	    new DialogListener() {
		@Override
		public void onComplete(Bundle values) {
		    SessionEvents.addAuthListener(new dogBoneAuthListener());
		    DogBoneServer.sendNewUserJson(FbRelatedStuff.facebook.getAccessToken());
		    SharedPreferences.Editor editor = mPrefs.edit();
		    editor.putString("access_token", FbRelatedStuff.facebook.getAccessToken());
		    editor.putLong("access_expires", FbRelatedStuff.facebook.getAccessExpires());
            
		    editor.commit();
		    mAsyncRunner.request("me", new meRequestListener());
		    Bundle params = new Bundle();
		    params.putString("method", "fql.query");
		    params.putString("query", "SELECT pic FROM user WHERE uid = me()");
		    mAsyncRunner.request(null, params, new FQLRequestListener());
		}
		
		@Override
		public void onFacebookError(FacebookError error) {}
		
		@Override
		public void onError(DialogError e) {}
		
		@Override
		public void onCancel() {}
	    });
	
        
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FbRelatedStuff.facebook.authorizeCallback(requestCode, resultCode, data);
	
    }
	/*
	 * Got what I need it's time to move on!!
	 * http://g.sevki.org/JDrWU5
	 */
    public void GoNoWGo() {
    	if(gotFbData && gotImagePath) {
    		pdialog.dismiss();
	    Intent intent =
		new Intent(this, team18.cs.ncl.ac.uk.MenuActivity.class);
	    startActivity(intent);
	}
    }
 
  
    public class FQLRequestListener extends BaseRequestListener {
	
        @Override
	public void onComplete(final String response, final Object state) {
	    
            /*
             * Output can be a JSONArray or a JSONObject.
             * Try JSONArray and if there's a JSONException, parse to JSONObject
             */
      
            try {
                JSONArray json = new JSONArray(response);
                FbRelatedStuff.ImagePath=json.getJSONObject(0).get("pic").toString();
                DogboneIslandActivity.gotImagePath=true;
		GoNoWGo();                
                
            } catch (JSONException e) {
                try {
                    /*
                     * JSONObject probably indicates there was some error
                     * Display that error, but for end user you should parse the
                     * error and show appropriate message
                     */
                    JSONObject json = new JSONObject(response);
                    FbRelatedStuff.ImagePath=json.getString("pic");
                    DogboneIslandActivity.gotImagePath=true;
		    GoNoWGo();	
                } catch (JSONException e1) {
                    Log.d("JSON ERROR:", e1.getMessage());
                }
            }
        }
	
        public void onFacebookError(FacebookError error) {
            Log.d("FACEBOOK ERROR:", error.getMessage());
        }
    }
    public class meRequestListener extends BaseRequestListener {
        public void onComplete(final String response, final Object state) {
	    
            try {
                // process the response here: executed in background thread
                Log.d("Facebook-Example", "Response: " + response.toString());
                JSONObject json = Util.parseJson(response);
                
                //get users info and save them to fbrelatedstuff.
                final String firstName = json.getString("first_name");
                final String lastName = json.getString("last_name");
                final String name = json.getString("name");
                final String gender = json.getString("gender");
                
                
                FbRelatedStuff.FirstName= firstName;
                FbRelatedStuff.LastName = lastName;
                FbRelatedStuff.Name = name;	
                
                FbRelatedStuff.Gender = team18.cs.ncl.ac.uk.FbRelatedStuff.gender.female;
                if(gender =="male")
		    FbRelatedStuff.Gender = team18.cs.ncl.ac.uk.FbRelatedStuff.gender.male;
                
                
                final long uid = json.getLong("id");
                FbRelatedStuff.uid=uid;
                DogboneIslandActivity.gotFbData=true;
		GoNoWGo();
	    } catch (JSONException e) {
                Log.w("Facebook-Example", "JSON Error in response");
                DogboneIslandActivity.this.runOnUiThread(new Runnable() {
			public void run() {
			    firstText.setText("FacebookError");
			}
		    });
                
            } catch (FacebookError e) {
                Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());
                DogboneIslandActivity.this.runOnUiThread(new Runnable() {
			public void run() {
			    firstText.setText("JSON");
			    
			}
		    });
            }
        }
    }
    
   
    public class dogBoneAuthListener implements AuthListener {
	
        public void onAuthSucceed() {
	    firstText.setText("You have logged in! ");
	    
        }
	
        public void onAuthFail(String error) {
	    
            firstText.setText("Login Failed: " + error);
        }
    }
    
}
