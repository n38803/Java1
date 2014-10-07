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

import java.util.ArrayList;


public class MyActivity extends Activity {

    final String TAG = "Java Project";
    private TextView userInput;
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
                Toast inputAlert = Toast.makeText(getApplicationContext(), "Item Added!", Toast.LENGTH_LONG);
                inputAlert.show();


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
                int avgLength = listLength / charLength;
    




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
