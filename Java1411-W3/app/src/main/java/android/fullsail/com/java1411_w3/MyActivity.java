package android.fullsail.com.java1411_w3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class MyActivity extends Activity {




    // assign views
    private Spinner hspinner;
    private ArrayList<Handset> handsetArrayList;
    private ListView hListView;
    public TextView hText;
    public int selected;
    final HashMap<Integer, Handset> hMap = new HashMap<Integer, Handset>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // orientation detection
        int orientation = getWindowManager().getDefaultDisplay().getRotation();

        selected = 0;

        handsetArrayList = new ArrayList<Handset>( ;
        ArrayAdapter<String> hAdapter;

                // assign object information
                Handset noselection = new Handset();
                noselection.setName("");
                noselection.setProcessor("");
                noselection.setRelease("");
                hMap.put(0, noselection);

                Handset isix = new Handset();
                isix.setName("iPhone 6");
                isix.setProcessor("Apple A8");
                isix.setRelease("Sept. 2014");
                hMap.put(1, isix);

                Handset iplus = new Handset();
                iplus.setName("iPhone 6Plus");
                iplus.setProcessor("Apple A8");
                iplus.setRelease("Sept. 2014");
                hMap.put(2, iplus);

                Handset gsfive = new Handset();
                gsfive.setName("Galaxy S5");
                gsfive.setProcessor("Krait 400");
                gsfive.setRelease("April 2014");
                hMap.put(3, gsfive);

                Handset notefour = new Handset();
                notefour.setName("Galaxy Note 4");
                notefour.setProcessor("Exynos Octa 7");
                notefour.setRelease("Oct. 2014");
                hMap.put(4, notefour);

                Handset noteedge = new Handset();
                noteedge.setName("Galaxy Note Edge");
                noteedge.setProcessor("Snapdragon 805");
                noteedge.setRelease("Nov. 2014");
                hMap.put(5, noteedge);



        if (orientation == 0)
        {
            // assign views
            hspinner = (Spinner) findViewById(R.id.spinner);
            hText = (TextView) findViewById(R.id.textView);

            // Adapter declaration
            ArrayList<String> hList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.handsets)));
            hAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, hList);

            // set adapter
            hspinner.setAdapter((hAdapter));



            // spinner event listener
            hspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                    // grabs selected item's position
                    selected = hspinner.getSelectedItemPosition();
                    setText();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                    hText.setText("Please make a selection.");

                }
            });
        }
        else if (orientation == 1)
        {

            setContentView(R.layout.landscape);

            hListView = (ListView) findViewById(R.id.landlistview);
            hText = (TextView) findViewById(R.id.landtextView);

            // Adapter declaration
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(getResources().getStringArray(R.array.handsets)));


            hListView.setAdapter(arrayAdapter);


            hListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    selected = i;
                    setText();

                 }
             });

        }






    }

    public void setText(){

        hText.setText("DEVICE NAME:\n\n" + hMap.get(selected).getName()
                        + "\n\n\nPROCESSOR:\n\n" + hMap.get(selected).getProcessor()
                        + "\n\n\nRELEASE DATE:\n\n" + hMap.get(selected).getRelease()
        );
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
