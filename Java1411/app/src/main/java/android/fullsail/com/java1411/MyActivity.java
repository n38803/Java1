package android.fullsail.com.java1411;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;



public class MyActivity extends Activity {

    Context context;

    // user input variables
    private TextView inputText;

    // data collection variables
    final LinkedHashSet<String> inputList = new LinkedHashSet<String>();
    private ListView visibleListView;
    private ArrayAdapter arrayAdapter;
    String complete = "";
    Object[] convertList;

    // math equation variables
    int listLength;
    int charLength;
    int average;
    int entryIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // assign activity context
        context = this;

        // assign input to data references for collection
        inputText = (TextView) findViewById(R.id.userInput);

        // assign button reference
        Button button = (Button) findViewById(R.id.submitButton);

        // initiate listview
        visibleListView = (ListView) findViewById(R.id.visibleList);


        // event listener for button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // add user text to hash set & clear visible entry
                inputList.add(inputText.getText().toString());
                clearInput();

                // used for debugging data collection issues
                for(String string : inputList) {
                    Log.d("HASH SET: ", string);
                }

                // concatenate strings within set
                concatenateFunction();

                // determine average length of characters
                findEntryCounts();

                // modify textviews
                TextView entryCount = (TextView) findViewById(R.id.totalEntries);
                entryCount.setText("Number of Entries: " + listLength);
                TextView avgLength = (TextView) findViewById(R.id.averageCharacters);
                avgLength.setText("Average Length of Entries: " + average);


                // convert set to string array
                String[] stringArray = inputList.toArray(new String[inputList.size()]);

                // set array adapter & connect to listview

                arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, stringArray);
                visibleListView.setAdapter(arrayAdapter);












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

    // CUSTOM FUNCTIONS ---------------------

    // clear user input
    private void clearInput() {
        inputText.setText("");
        complete = "";
    }

    private void concatenateFunction (){

        // Concatenate all strings in the set list
        for(String string : inputList) {
            complete += string;
        }

    }

    private void findEntryCounts (){

        // determine average character amount
        listLength = inputList.size();
        charLength = complete.length();
        average = charLength / listLength;



    }


    private void populateList() {

        // convert set to string array
        String[] stringArray = inputList.toArray(new String[inputList.size()]);

        // initiate & display listview
        visibleListView = (ListView) findViewById(R.id.visibleList);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray);
        visibleListView.setAdapter(arrayAdapter);
    }


}
