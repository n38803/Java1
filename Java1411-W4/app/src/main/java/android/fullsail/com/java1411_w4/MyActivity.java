package android.fullsail.com.java1411_w4;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Assign connectivity manager
        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        // find active connection type (assigned to object)
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        // Verify whether or not connection exists
        if(netInfo != null) {
            if(netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

                // Connected to mobile data

            } else if(netInfo.getType() == ConnectivityManager.TYPE_WIFI) {

                // Connected to WiFi data
            }

            if(netInfo.isConnected()) {

                // Connected to data type without bias

            }
        else if (netInfo == null) {

                // There is no data connection
            }
        }






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
