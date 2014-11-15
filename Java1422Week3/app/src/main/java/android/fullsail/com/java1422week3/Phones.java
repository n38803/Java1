// Phones.java


public class Phone {

    // Phone variables
    private String mName;
    private String mRelease;
    private String mProcessor;


    // Class constructor
    public Phone() {
        mName = mRelease = mProcessor = "";
    }
    public Phone(String _name, String _release, String _processor) {
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


}