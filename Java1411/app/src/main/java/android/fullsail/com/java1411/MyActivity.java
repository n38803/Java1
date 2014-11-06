package android.fullsail.com.java1411;

import android.app.Activity;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.app.AlertDialog;


import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;


public class MyActivity extends Activity {

    // Global
    private TextView inputText;


    // data collection variables
    final LinkedHashSet<String> inputList = new LinkedHashSet<String>();
    private ListView visibleListView;
    private ArrayAdapter arrayAdapter;
    String complete = "";
    Object[] convertList;
    private boolean result = false;

    // math equation variables
    int listLength;
    int charLength;
    int average;
    int entryIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);



        // assign input to data references for collection
        inputText = (TextView) findViewById(R.id.userInput);

        // assign button reference
        Button button = (Button) findViewById(R.id.submitButton);

        // event listener for button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // initiate error alert builder
                AlertDialog.Builder errorAlert = new AlertDialog.Builder(view.getContext());


                if (inputText.getText().toString().equals(""))
                {
                    errorAlert.setTitle("ERROR:");
                    errorAlert.setMessage("Entries cannot be left Blank! Please try again.");
                    errorAlert.setNeutralButton("OK", null);

                    AlertDialog dialog = errorAlert.create();
                    dialog.show();
                }
                else{

                    // add user text to hash set & clear visible entry
                    inputList.add(inputText.getText().toString());
                    clearInput();

                    // populate listview
                    populateList();

                    // concatenate strings within set
                    concatenateFunction();

                    // determine average length of characters
                    findEntryCounts();

                    // modify textviews
                    TextView entryCount = (TextView) findViewById(R.id.totalEntries);
                    entryCount.setText("Total Entries: " + listLength);
                    TextView avgLength = (TextView) findViewById(R.id.averageCharacters);
                    avgLength.setText("Average Characters: " + average);

                }

            }

        });

        // set event listener for item selection
        selectionAlert();



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
        String [] stringArray = inputList.toArray(new String[inputList.size()]);

        // build array adapter
        arrayAdapter = new ArrayAdapter(MyActivity.this, android.R.layout.simple_list_item_1, stringArray);

        // configure & display listview
        visibleListView = (ListView) findViewById(R.id.visibleList);
        visibleListView.setAdapter(arrayAdapter);
    }

    private void selectionAlert(){

        visibleListView = (ListView) findViewById(R.id.visibleList);
        visibleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView selected = (TextView) view;
                final String selection = selected.getText().toString();

                // initiate alert
                AlertDialog.Builder selectionAlert = new AlertDialog.Builder(view.getContext());

                // convert set in order to locate & assign index
                convertList = inputList.toArray();
                String [] stringArray = inputList.toArray(new String[inputList.size()]);


                List<String> indexList = Arrays.asList(stringArray);

                // assign object index to integer for alert field
                entryIndex = indexList.indexOf(selection);



                // assign alert fields
                selectionAlert.setTitle("ALERT!");
                selectionAlert.setMessage("You have selected: " + selection);
                selectionAlert.setNeutralButton("OK", null);
                selectionAlert.setNegativeButton("Remove",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            // remove selection from hash set
                            inputList.remove(selection);

                            // refresh listview
                            populateList();

                        }
                    }
                );









                // display alert
                AlertDialog dialog = selectionAlert.create();
                dialog.show();

            }
        });


    }




}
