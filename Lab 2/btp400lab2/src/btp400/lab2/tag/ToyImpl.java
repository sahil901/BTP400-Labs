package btp400.lab2.tag;

import btp400.lab2.items.Toy;
import java.util.Objects;

public class ToyImpl extends Toy implements Taggable {
    private String tag;
    /**
     * @param name
     * @param ageLimitLow
     * @param ageLimitHigh
     */
    public ToyImpl(String name, int ageLimitLow, int ageLimitHigh) {
        super(name, ageLimitLow, ageLimitHigh);
        generateTag();
    }

    @Override
    public void generateTag() {
        tag = getName().toUpperCase()+"."+getAgeLimitLow()+"."+getAgeLimitHigh();
    }

    /**
     * All toys are for children, So always returns true
     * @return
     */
    @Override
    public boolean isChildTag() {
        return true;
    }

    @Override
    public String getTag() {
        return tag;
    }

     // method for comapring 2 objects
    @Override
    public boolean equals(Object o) {
        ToyImpl toy = (ToyImpl) o;
        return super.equals(o) && Objects.equals(getTag(), toy.getTag());
    }

    // hashing code
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAgeLimitLow(), getAgeLimitHigh(),getTag());
    }

 // returns string repersentation of the obj
    @Override
    public String toString() {
        return "Tag#"+tag+": "+super.toString();
    }
}
