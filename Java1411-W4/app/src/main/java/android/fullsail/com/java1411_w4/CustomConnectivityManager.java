package android.fullsail.com.java1411_w4;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Shaun on 11/20/2014.
 */
public class CustomConnectivityManager {

    Context context;
    Boolean connected;


    // Class constructor
    public boolean CustomConnectivityManager() {

        // Getting our connectivity manager.
        ConnectivityManager mgr = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Getting our active network information.
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        // check for connection
        if(netInfo != null){

            // verify DATA connection
            if (netInfo.isConnected()){
                connected = true;

            }
            else {
                connected = false;
            }
        }

        return connected;


    }




}
