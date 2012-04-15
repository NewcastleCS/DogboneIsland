package team18.cs.ncl.ac.uk;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.os.Looper;

public class DogBoneServer {

	protected static void sendJson(final long User_Id, final int Game_id, final String Caption,final int Win) {
      
		  Thread t = new Thread(){
		        public void run() {
		                Looper.prepare(); //For Preparing Message Pool for the child Thread
		                HttpClient client = new DefaultHttpClient();
		                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
		                HttpResponse response;
		                JSONObject json = new JSONObject();
		                try{
		                    HttpPost post = new HttpPost("http://ncl.sevki.org/newWin.php");
		                    //HttpPost post = new HttpPost("http://10.0.2.2:1337");
		                    json.put("user_id", User_Id);
		                    json.put("game_id", Game_id);
		                    json.put("caption", Caption);
		                    json.put("win", Win);
		                    StringEntity se = new StringEntity(  json.toString());  
		                    se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		                    post.setEntity(se);
		                    response = client.execute(post);
		      
		                    /*Checking response */
		                    if(response!=null){
		                    	System.out.println("oldu gibi ama");
		                       InputStream in = response.getEntity().getContent(); //Get the data in the entity
		                       int b;
		                       String cq = "";
		                       while ( ( b = in.read() ) != -1 )
		                       {

		                           char c = (char)b;
		                           cq =cq+c;
		                       }
		                       System.out.println(cq);
		                    }
		                    else
		                    {
		                    	System.out.println("olmadi");
		                    }
		                }
		                catch(Exception e){
		                    e.printStackTrace();
		               
		                }
		                Looper.loop(); //Loop in the message queue
		            }
		        };
		        t.start();         

}
}