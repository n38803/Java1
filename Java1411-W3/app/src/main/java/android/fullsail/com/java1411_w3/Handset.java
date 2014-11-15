package android.fullsail.com.java1411_w3;

/**
 * Created by Shaun on 11/15/2014.
 */
public class Handset {

    // Phone variables
    private String mName;
    private String mRelease;
    private String mProcessor;


    // Class constructor
    public Handset() {
        mName = mRelease = mProcessor = "";
    }
    public Handset(String _name, String _release, String _processor) {
        mRelease = _release;
        mName = _name;
        mProcessor = _processor;
    }
    // Getter Methods
    public String getName() {
        return mName;
    }
    public String getRelease() {
        return mRelease;
    }
    public String getProcessor() {
        return mProcessor;
    }


    // Setter Methods
    public void setName(String _name) {
        mName = _name;
    }
    public void setRelease(String _release) {
        mRelease = _release;
    }
    public void setProcessor(String _processor) {
        mProcessor = _processor;
    }

    @Override
    public String toString() {
        return "Device: " + mName + "\n Processor: " + mProcessor + "\n Release Year: " + mRelease;
    }

}
