package android.fullsail.com.java1week1;

import android.app.Activity;
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


public class MyActivity extends Activity {

    final String TAG = "Java Project";
    private TextView userInput;
    private TextView indexInput;
    int entryNum;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //set references to user and result text field
        userInput = (TextView) findViewById(R.id.usertxt);
        final ArrayList<String> inputList = new ArrayList<String> ();

        // button event listener - user input for collection
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Button Clicked");

                // add text to array
                inputList.add(userInput.getText().toString());

                // add alert that item has been added
                Toast inputAlert = Toast.makeText(getApplicationContext(), userInput.getText() + " Added!", Toast.LENGTH_LONG);
                inputAlert.show();

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

        //set references to user and result text field
        indexInput = (TextView) findViewById(R.id.indexinput);

        Button sbutton = (Button) findViewById(R.id.sbutton);
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // find index and display in alert
                

            }


        });


        /*// button event listener - user input for collection
        Button sbutton = (Button) findViewById(R.id.sbutton);
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Search Button Clicked");

                // add alert that item has been added
                Toast indexAlert;
                indexAlert = Toast.makeText(getApplicationContext(), "Item: " + indexInput.getText() + " = "inputList[indexInput.getText()], Toast.LENGTH_LONG);
                indexAlert.show();

                // display index selection in alert







            }







        });
*/

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
