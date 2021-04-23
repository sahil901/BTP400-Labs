package btp400.lab2.tag;

public interface Taggable {
    /**
     * Produce a sequence of digits based on the instance variable
     */
    void generateTag();
    /**
     * Identifies whether object is intended to be for children
     */
    boolean isChildTag();

    /**
     * Retrieve generated tag
     * @return
     */
    String getTag();
}
