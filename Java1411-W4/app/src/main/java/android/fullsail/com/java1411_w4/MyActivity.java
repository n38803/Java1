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

import org.apache.commons.compress.utils.IOUtils;
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

        // Verify whether or not connection exists
        if(netInfo != null) {
            if(netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

                // Connected to mobile data

            } else if(netInfo.getType() == ConnectivityManager.TYPE_WIFI) {

                // Connected to WiFi data
            }

            if(netInfo.isConnected()) {

                Button searchButton = (Button) findViewById(R.id.button);
                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView userInput = (TextView) findViewById(R.id.userinput);
                        String word = userInput.getText().toString();
                        try {
                            String baseURL = "http://words.bighugelabs.com/api/2/7b7810fb805241407b7d474b9b8ccfef/";
                            URL queryURL = new URL(baseURL + word + "/json");

                        } catch (Exception e) {
                            Log.e(TAG, "Invalid query for word: " + word);
                        }
                    }
                });

            }
        else if (netInfo == null) {

                // There is no data connection
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

    private class GetWordTask extends AsyncTask<URL, Integer, JSONObject> {
        final String TAG = "ASYNCTASK DEBUGGING";

        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonString = "";

            // COLLECT STRING RESPONSE FROM API
            for(URL queryURL : urls){
                try{
                    URLConnection datacon = queryURL.openConnection();
                    jsonString = IOUtils.toString(datacon.getInputStream());
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

            } catch (Exception e) {
                Log.e(TAG, "Cannot convert API response to JSON");
                apiData = null;
            }

            try{
                apiData = (apiData != null) ? apiData.getJSONObject("").getJSONObject("") : null;
                Log.i(TAG, "API JSON data received: " + apiData.toString());
            } catch (Exception e) {
                Log.e(TAG, "Could not parse data record from response: " + apiData);
                apiData = null;
            }

            return apiData;
        }

        protected void onPostExecute(JSONObject apiData) {

            // this is where you populate your object and push to UI
            Thesaurus result = new Thesaurus(apiData);
            updateDisplay(result);

        }


    }
}
