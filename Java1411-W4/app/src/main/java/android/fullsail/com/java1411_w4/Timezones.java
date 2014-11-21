package android.fullsail.com.java1411_w4;

/**
 * Created by Shaun on 11/20/2014.
 */
public class Timezones {

    // Descriptive variables
    private String mZone;
    private String mID;
    private String mStatus;
    private String mError;


    // Class constructor
    public Timezones() {
        mZone = mID  = mStatus = mError = "";
    }
    public Timezones(String _zone, String _id, String _status, String _error) {
        mZone = _zone;
        mID = _id;
        mStatus = _status;
        mError = _error;

    }


    // Getter Methods
    public String getZone() {
        return mZone;
    }
    public String getID() {
        return mID;
    }
    public String getStatus() {
        return mStatus;
    }
    public String getError() {
        return mError;
    }




    // Setter Methods
    public void setZone(String _zone) {
        mZone = _zone;
    }
    public void setID(String _id) {
        mID = _id;
    }
    public void setStatus(String _status) {
        mStatus = _status;
    }
    public void setError(String _error) {
        mError = _error;
    }



    @Override
    public String toString() {
        return "Zone: " + mZone + "\n ID: " + mID + "\nStatus: " + mStatus + "\nError: " + mError;
    }


}
