package android.fullsail.com.java1411_w4;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;


public class MyActivity extends Activity {

    final String TAG = "DEBUGGING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Assign connectivity manager
        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        // find active connection type (assigned to object)
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        // IF CONNECTION DETECTED
        if(netInfo != null) {

            Log.i(TAG, "Connection detected!");

            if(netInfo.isConnected()) {

                Log.i(TAG, "isConnected Complete");

                Button searchButton = (Button) findViewById(R.id.button);
                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView userInput = (TextView) findViewById(R.id.userinput);
                        String userWord = userInput.getText().toString();
                        try {
                            String baseURL = "http://words.bighugelabs.com/api/2/7b7810fb805241407b7d474b9b8ccfef/";
                            URL queryURL = new URL(baseURL + userWord + "/json");

                        } catch (Exception e) {
                            Log.e(TAG, "Invalid query for word: " + userWord);
                        }
                    }
                });

            }

        // IF NO DATA CONNECTION FOUND
        else if (netInfo == null) {

                // pop up error
                Log.e(TAG, "No data network connection detected");
            }
        }






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void updateDisplay(Thesaurus word){
        ((TextView) findViewById(R.id.word)).setText((word.getName()));
        ((TextView) findViewById(R.id.type)).setText((word.getDescription()));
        //((TextView) findViewById(R.id.result)).setText((word.getDescription()));

    }




    private class GetWordTask extends AsyncTask<URL, Integer, JSONObject> {
        final String TAG = "ASYNCTASK DEBUGGING";

        @Override
        protected JSONObject doInBackground(URL... urls) {

            Log.i(TAG, "You are now in background computing");

            String jsonString = "";

            // COLLECT STRING RESPONSE FROM API
            for(URL queryURL : urls){
                try{
                    URLConnection connected = queryURL.openConnection();
                    jsonString = IOUtils.toString(connected.getInputStream());
                    Log.i(TAG, "URL query successful - " + jsonString);
                    break;
                } catch (Exception e){
                    Log.e(TAG, "Could not establish URLConnection to " + queryURL.toString());
                }
            }

            Log.i(TAG, "Received Data: " + jsonString);


            // CONVERT API STRING RESPONSE TO JSONOBJECT

            JSONObject apiData;

            try{
                apiData = new JSONObject(jsonString);
                Log.i(TAG, "Object creation Complete");

            } catch (Exception e) {
                Log.e(TAG, "Cannot convert API response to JSON");
                apiData = null;
            }

            try{
                apiData = (apiData != null) ? apiData.getJSONObject("noun").getJSONObject("syn") : null;
                Log.i(TAG, "API JSON data received: " + apiData.toString());
            } catch (Exception e) {
                Log.e(TAG, "Could not parse data record from response: " + apiData);
                apiData = null;
            }

            return apiData;
        }

        protected void onPostExecute(JSONObject apiData) {
            Log.i(TAG, "You have made it to post execution");
            // this is where you populate your object and push to UI
            //Thesaurus result = new Thesaurus(apiData);
            //updateDisplay(result);

        }


    }
}
