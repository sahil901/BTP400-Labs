package btp400.lab2.abstractPackage;

public abstract class AbstractTaggable{
    private String tag;

    
    // set tag to the current instance of tag
    // setter method
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Produce a sequence of digits based on the instance variable
     */
    public abstract void generateTag();
    /**
     * Identifies whether object is intended to be for children
     */
    public abstract boolean isChildTag();

    /**
     * Retrieve generated tag
     * @return
     */
    public String getTag() {
        return tag;
    }
}
