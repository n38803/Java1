package android.fullsail.com.java1411_w3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class MyActivity extends Activity {




    // assign views
    private Spinner hspinner;
    private ArrayList handsetArrayList;
    // ListView listView = (ListView) findViewById(R.id.hList);
    public TextView hText;



    // test of desperation ==================================================



    public class ExampleAdapter extends BaseAdapter {
        private static final long ID_CONSTANT = 0x010101010L;
        private Context mContext;
            private ArrayList<Handset> mHandsets;
            // We take in a context and list of Employee objects.
            // The list is our backing collection and the context is used
            // to create new views in our getView() method.
            public ExampleAdapter(Context _context, ArrayList<Handset> _handsets) {
                mContext = _context;
                mHandsets = _handsets;
        }
        // Returning the number of objects in our collection.
        @Override
        public int getCount() {
            return mHandsets.size();
        }
        // Returning Employee objects from our collection.
        @Override
        public Handset getItem(int _position) {
            return mHandsets.get(_position);
        }
        // Adding our constant and position to create unique ID values.
        @Override
        public long getItemId(int _position) {
            return ID_CONSTANT + _position;
        }
        @Override
        public View getView(int _position, View _convertView, ViewGroup _parent) {
            // If we don't have a recycled view, create a new view.
            if(_convertView == null) {
                // Creating the new view.
                _convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_my, _parent, false);
            }
            // Get the object from the collection in an index-safe manner.
            Handset handset = getItem(_position);


            // Use the object from the collection to fill out our view.
            TextView tv = (TextView)_convertView.findViewById(R.id.textView);
            tv.setText(handset.getName());
          //  tv = (TextView)_convertView.findViewById(R.id.employee_department);
           // tv.setText(employee.getDepartment());
           // tv = (TextView)_convertView.findViewById(R.id.employee_service_time);
           // tv.setText(mContext.getString(R.string.years, employee.getYearsOfService()));

            // Returning our filled out view.
            return _convertView;
        }


        private void setSimpleAdapter() {
            // Field identifiers
            final String name = "name";
            final String processor = "processor";
            final String release = "release";
            // List of elements for our adapter.
            ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String,String>>();
            // Goes through each employee and maps the data elements to a String key.
            for(Handset handset : mHandsets) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(name, handset.getName());
                map.put(processor, handset.getProcessor());
                map.put(release, handset.getRelease());
                elements.add(map);
            }
            // Creating an array of our keys
            String[] keys = new String[] {
                    name, processor, release
            };
            // Creating an array of our list item components.
            // Indices must match the keys array.
            int[] views = new int[] {
                    R.id.textView
            };
            // Creating a new SimpleAdapter that maps values to views using our keys and views arrays.
            SimpleAdapter adapter = new SimpleAdapter(mContext, elements, R.layout.activity_my, keys, views);
            hspinner.setAdapter(adapter);
        }
    }





    // ===========================================================================












    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        hspinner = (Spinner) findViewById(R.id.spinner);
        handsetArrayList = new ArrayList<Handset>();
        ArrayAdapter<String> hAdapter;






















        final HashMap<Integer, Handset> hMap = new HashMap<Integer, Handset>();
        hText = (TextView) findViewById(R.id.textView);


        // assign object information
        Handset isix = new Handset();
        isix.setName("iPhone 6");
        isix.setProcessor("A6");
        isix.setRelease("Sept.2014");
        hMap.put(1, isix);

        Handset iplus = new Handset();
        iplus.setProcessor("A6");
        iplus.setRelease("Sept.2014");
        hMap.put(2, iplus);

        Handset gsfive = new Handset();
        gsfive.setName("Galaxy S5");
        gsfive.setProcessor("A6");
        gsfive.setRelease("Sept.2014");
        hMap.put(3, gsfive);

        Handset notefour = new Handset();
        notefour.setName("Galaxy Note 4");
        notefour.setProcessor("A6");
        notefour.setRelease("Sept.2014");
        hMap.put(4, notefour);

        Handset noteedge = new Handset();
        noteedge.setName("Galaxy Note Edge");
        noteedge.setProcessor("A6");
        noteedge.setRelease("Sept.2014");
        hMap.put(5, noteedge);






        // test assignment to Spinner.
        ArrayList<String> hList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.handsets)));

        hAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, hList);

        hspinner.setAdapter((hAdapter));

        // spinner event listener
        hspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                // grabs selected item's position
                int selected = hspinner.getSelectedItemPosition();
                // String selection = hMap.get(selected).toString();

               // hText.setText("Name: " + hMap.get(selected).getName() + "\nProcessor: " + hMap.get(selected).getProcessor() + "\nRelease: " + hMap.get(selected).getRelease());


                // change textview to match item position (matches hashmap key)
               // hText.setText(name);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                hText.setText("Please make a selection.");

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
