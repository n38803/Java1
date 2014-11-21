package android.fullsail.com.java1411_w4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;


public class MyActivity extends Activity {

    // variables
    final String TAG = "DEBUGGING";
    public String longitude;
    public String latitude;
    public URL queryURL;
    public boolean isConnected;


    // views
    public TextView longInput;
    public TextView latInput;
    public Button longSubmit;
    public Button latSubmit;
    public Button searchButton;


    // check connection from Custom Helper
    private boolean checkConnection(CustomConnectivityManager connectionCheck){
        if(connectionCheck.connected = true){
            isConnected = true;
            Log.i(TAG, "Connection detected!");
            return isConnected;
        }
        else {
            isConnected = false;
            Log.i(TAG, "No Connection detected!");
            return isConnected;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        // Assign connectivity manager
        //ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        // find active connection type (assigned to object)
        //NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

       // run connection check
        //checkConnection(CustomConnectivityManager.);

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

                // feedback based on user input
                Toast.makeText(getApplicationContext(), ("Longitude Saved:  [ " + longitude + " ]"),
                        Toast.LENGTH_SHORT).show();
            }

        });


        // LONGITUDE button event listener
        latSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //grab user input for latitude
                latitude = latInput.getText().toString();

                // feedback based on user input
                Toast.makeText(getApplicationContext(), ("Latitude Saved:  [ " + latitude + " ]"),
                        Toast.LENGTH_SHORT).show();
            }

        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // IF CONNECTION DETECTED
                if(isConnected = true) {

                    Log.i(TAG, "Connection: "  + isConnected);


                    // ERROR DETECTION FOR LACK OF USER INPUT
                    if (longitude != null && latitude != null) {

                        try {

                            String baseURL = "https://maps.googleapis.com/maps/api/timezone/json?location=";
                            String timestamp = "timestamp=1331161200";
                            String key = "AIzaSyAvhB5TTfvZY3QQFUgIP_l4-0eTKIDkSdM";
                            // example URL: https://maps.googleapis.com/maps/api/timezone/json?location=LONGTITUDE,LATITUDE&timestamp=1331161200&key=API_KEY

                            queryURL = new URL(baseURL + longitude + "," + latitude + "&" + timestamp + "&" + key);
                            Log.i(TAG, "URL: " + queryURL);

                            // execute task
                            new GetTimezoneTask().execute(queryURL);

                            // reset input
                            latInput.setText("");
                            longInput.setText("");


                        } catch (Exception e) {
                            Log.e(TAG, "Invalid query for location: " + longitude + " , " + latitude);
                        }


                    } else {


                        // initiate alert
                        AlertDialog.Builder inputAlert = new AlertDialog.Builder(v.getContext());

                        // assign alert fields
                        inputAlert.setTitle("ERROR");
                        inputAlert.setMessage("One or more entries have not been saved.  Please click 'SAVE' and try again.");
                        inputAlert.setNeutralButton("Ok", null);

                        AlertDialog idialog = inputAlert.create();
                        idialog.show();

                        Log.e(TAG, "Invalid Longitude: " + longitude + " & Invalid Latitude: " + latitude);

                    }
                }

                // IF NO DATA CONNECTION FOUND
                else if (isConnected = false) {

                    // initiate alert
                    AlertDialog.Builder connectionAlert = new AlertDialog.Builder(v.getContext());

                    // assign alert fields
                    connectionAlert.setTitle("NO DATA CONNECTION DETECTED");
                    connectionAlert.setMessage("Please connect to a network & try again");
                    connectionAlert.setNeutralButton("Ok", null);

                    AlertDialog cdialog = connectionAlert.create();
                    cdialog.show();

                    Log.e(TAG, "No data network connection detected");
                }

            }
        });










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

        if(timezone.getZone() != null)
        {
            ((TextView) findViewById(R.id.timezone)).setText((timezone.getZone()));
            ((TextView) findViewById(R.id.timetype)).setText(("Time Zone ID: " + timezone.getID()));
            ((TextView) findViewById(R.id.status)).setText(("Query Status: " + timezone.getStatus()));
            ((TextView) findViewById(R.id.error)).setText("");
        }

        else {

            // initiate alert
            AlertDialog.Builder invalid = new AlertDialog.Builder(this);

            // assign alert fields
            invalid.setTitle("INVALID LOCATION SETTINGS");
            invalid.setMessage("Please correct your longitude and/or latitude and try again.");
            invalid.setNeutralButton("Ok", null);

            AlertDialog invDialog = invalid.create();
            invDialog.show();

            ((TextView) findViewById(R.id.timezone)).setText("");
            ((TextView) findViewById(R.id.timetype)).setText("");
            ((TextView) findViewById(R.id.status)).setText("Query Status: REQUEST_DENIED" );
            ((TextView) findViewById(R.id.error)).setText("Error: Invalid request. Invalid 'location' parameter.");
        }

    }




    private class GetTimezoneTask extends AsyncTask<URL, Integer, JSONObject> {

        final String TAG = "ASYNCTASK DEBUGGING";
        public Toast progress;



        @Override
        protected void onPreExecute(){


            progress = Toast.makeText(getApplicationContext(), "Your request is processing . . .",
                    Toast.LENGTH_LONG);
            progress.setGravity(Gravity.BOTTOM|Gravity.LEFT, 200, 800);
            progress.show();

        }


        @Override
        protected JSONObject doInBackground(URL... urls) {

            Log.i(TAG, "You are now in background computing\nBG URL: " + queryURL);

            String jsonString = "";

            // COLLECT STRING RESPONSE FROM API
            for(URL queryURL : urls){
                try{

                    // open connection based on https URL assigned above
                    HttpsURLConnection connection = (HttpsURLConnection) queryURL.openConnection();

                    // pull api info and assign to string object
                    jsonString = IOUtils.toString(connection.getInputStream());

                    Log.i(TAG, "API Pull Success.");
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
                apiData = (apiData != null) ? apiData : null;
                Log.i(TAG, "API JSON data received: " + apiData.toString());
            } catch (Exception e) {


                Log.e(TAG, "Could not parse data record from response: " + apiData);
                apiData = null;
            }

            return apiData;
        }


        protected void onPostExecute(JSONObject apiData) {

            progress.cancel();

            Log.i(TAG, "You have made it to post execution");

            // this is where you populate your object and push to UI
            Timezones result = new Timezones(apiData);
            updateDisplay(result);

        }


    }
}
