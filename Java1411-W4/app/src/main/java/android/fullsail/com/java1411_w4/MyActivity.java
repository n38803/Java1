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

    // variables
    final String TAG = "DEBUGGING";
    public String longitude;
    public String latitude;

    // views
    public TextView longInput;
    public TextView latInput;
    public Button longSubmit;
    public Button latSubmit;
    public Button searchButton;


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

                // Build views
                longInput = (TextView) findViewById(R.id.longinput);
                latInput = (TextView) findViewById(R.id.latinput);
                longSubmit = (Button) findViewById(R.id.longsubmit);
                latSubmit = (Button) findViewById(R.id.latsubmit);
                searchButton = (Button) findViewById(R.id.search);


                // LONGITUDE button event listener
                longSubmit.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {

                          //grab user input for longitude
                          longitude = longInput.getText().toString();
                      }

                });


                // LONGITUDE button event listener
                latSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //grab user input for latitude
                        latitude = latInput.getText().toString();
                    }

                });




                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // ERROR DETECTION FOR LACK OF USER INPUT
                        if (longitude != null && latitude != null){

                            try {

                                String baseURL = "https://maps.googleapis.com/maps/api/timezone/json?location=";
                                String timestamp = "timestamp=1331161200";
                                String key = "AIzaSyAvhB5TTfvZY3QQFUgIP_l4-0eTKIDkSdM";
                                // example URL: https://maps.googleapis.com/maps/api/timezone/json?location=LONGTITUDE,LATITUDE&timestamp=1331161200&key=API_KEY

                                URL queryURL = new URL(baseURL + longitude + "," + latitude + "&" + timestamp + "&" + key);
                                Log.i(TAG, "URL: " + queryURL);

                            } catch (Exception e) {
                                Log.e(TAG, "Invalid query for location: " + longitude + " , " + latitude);
                            }



                        }

                        else {

                            // ERROR POP UP
                            Log.e(TAG, "Invalid Longitude: " + longitude + " & Invalid Latitude: " + latitude);

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


    private void updateDisplay(Timezones timezone){
        ((TextView) findViewById(R.id.timezone)).setText((timezone.getZone()));
        ((TextView) findViewById(R.id.timetype)).setText((timezone.getID()));
        ((TextView) findViewById(R.id.status)).setText((timezone.getStatus()));
        ((TextView) findViewById(R.id.error)).setText((timezone.getError()));
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
