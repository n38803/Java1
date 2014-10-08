package android.fullsail.com.java1week1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;


public class MyActivity extends Activity {

    final String TAG = "Java Project";
    private TextView userInput;
    private TextView indexInput;
    int entryNum;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //set reference to user text field
        userInput = (TextView) findViewById(R.id.usertxt);

        // create set
        final LinkedHashSet<String> inputList = new LinkedHashSet<String>();

        // button event listener - user input for collection
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // add text to array
               inputList.add(userInput.getText().toString());

                // add alert that item has been added
                Toast inputAlert = Toast.makeText(getApplicationContext(), "Data Updated.", Toast.LENGTH_LONG);
                inputAlert.show();


               // clear edittext field upon button click
               userInput.setText("");

               // Concatenate all strings in the list
               String complete = "";
               for(String string : inputList) {
                   complete += string;
               }

               //determine length of array
               int listLength = inputList.size();

               // determine character count of array
               int charLength = complete.length();

               // find average length of strings (divide char count in
               // concatenation with total number of entries
               int average = charLength / listLength;

               // modify entryNum textview
               TextView entryNum = (TextView) findViewById(R.id.entryNum);
               entryNum.setText("Number of Entries: " + listLength);

               // modify avgLength textview
               TextView avgLength = (TextView) findViewById(R.id.avgLength);
               avgLength.setText("Average Length of Entries: " + average);

            }




        });

        //set reference to index text field
        indexInput = (TextView) findViewById(R.id.indexinput);

        Button sbutton = (Button) findViewById(R.id.sbutton);
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // show input based on index selection
                AlertDialog.Builder entryAlert = new AlertDialog.Builder(view.getContext());

                // convert charsequence to string for alert text & int for indexing.
                String entryString = indexInput.getText().toString();
                int entryInt = Integer.parseInt(entryString);

                // conditional - error detection for entry input.
                if(entryInt <= inputList.size()) {

                    // convert set to array
                    Object[] convertList = inputList.toArray();

                    entryAlert.setTitle("Index: " + indexInput.getText());
                    entryAlert.setMessage("Item: " + convertList[entryInt]);
                    entryAlert.setNeutralButton("OK", null);

                    AlertDialog dialog = entryAlert.create();
                    dialog.show();

                    indexInput.setText("");
                }
                else {
                    entryAlert.setTitle("ERROR!");
                    entryAlert.setMessage("No index found. Please try again!");
                    entryAlert.setNeutralButton("OK", null);

                    AlertDialog dialog = entryAlert.create();
                    dialog.show();

                    indexInput.setText("");

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
}
