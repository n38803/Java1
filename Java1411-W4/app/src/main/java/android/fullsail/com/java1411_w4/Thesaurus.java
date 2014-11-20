package android.fullsail.com.java1411_w4;

/**
 * Created by Shaun on 11/20/2014.
 */
public class Thesaurus {


    // Descriptive variables
    private String mName;
    private String mDescription;


    // Class constructor
    public Thesaurus() {
        mName = mDescription  = "";
    }
    public Thesaurus(String _name, String _description) {
        mName = _name;
        mDescription = _description;
    }
    // Getter Methods
    public String getName() {
        return mName;
    }
    public String getmDescription() {
        return mDescription;
    }



    // Setter Methods
    public void setName(String _name) {
        mName = _name;
    }
    public void setmDescription(String _description) {
        mDescription = _description;
    }


    @Override
    public String toString() {
        return "Word: " + mName + "\n Description: " + mDescription;
    }

}
