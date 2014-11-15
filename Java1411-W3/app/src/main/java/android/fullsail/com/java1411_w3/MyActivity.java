package android.fullsail.com.java1411_w3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;


public class MyActivity extends Activity {

    // assign views
    private Spinner hspinner;
    private ArrayList handsetArrayList;
    // ListView listView = (ListView) findViewById(R.id.hList);









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        hspinner = (Spinner) findViewById(R.id.spinner);
        handsetArrayList = new ArrayList<Handset>();
        ArrayAdapter<String> hAdapter;

        // assign object information
        handsetArrayList.add(new Handset("iPhone 6", "A8", "Sept. 2014"));
        handsetArrayList.add(new Handset("iPhone 6Plus", "A8", "Sept. 2014"));
        handsetArrayList.add(new Handset("Galaxy S5", "Snapdragon", "May 2014"));
        handsetArrayList.add(new Handset("Galaxy Note 4", "Snapdragon", "Oct. 2014"));
        handsetArrayList.add(new Handset("Galaxy Note 4 Edge", "Snapdragon 508", "Nov. 2014"));


        // test assignment to Spinner.
        ArrayList<String> hList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.handsets)));

        hAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, hList);

        hspinner.setAdapter((hAdapter));

        // spinner event listener
        hspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                // grabs selected item's position
                hspinner.getSelectedItemPosition();


                // change textview to match item position (matches hasmap key)

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
}
