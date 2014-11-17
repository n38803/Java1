package android.fullsail.com.java1422week3;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MyActivity extends Activity {

    // Views
    private Spinner handsetSpinner;
    private ListView handsetListView;
    private TextView test;


    // Data Collection Assignment
    public HashMap<String, Handset> handsetMap = new HashMap<String, Handset>();
    private ArrayList<Handset> handsetArrayList;



    // orientation detection
    Display getOrient = getWindowManager().getDefaultDisplay();
    int orientation = getOrient.getRotation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // assign views
        handsetListView = (ListView) findViewById(R.id.hList);
        handsetSpinner = (Spinner) findViewById(R.id.spinner);
        handsetArrayList = new ArrayList<Handset>();

        // assign object information
        handsetArrayList.add(new Handset("iPhone 5c", "A6", "Sept. 2013"));
        handsetArrayList.add(new Handset("iPhone 5s", "A7", "Sept. 2013"));
        handsetArrayList.add(new Handset("iPhone 6", "A8", "Sept. 2014"));
        handsetArrayList.add(new Handset("iPhone 6plus", "A8", "Sept. 2014"));
        handsetArrayList.add(new Handset("Galaxy S5", "Snapdragon", "May 2014"));
        handsetArrayList.add(new Handset("Galaxy Note 4", "Snapdragon", "Oct. 2014"));



        String test = String.valueOf(orientation);

       Log.d("Orientation: ", test);







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
}