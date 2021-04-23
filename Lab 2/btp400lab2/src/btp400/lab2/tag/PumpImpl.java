package btp400.lab2.tag;

import btp400.lab2.items.Pump;
import btp400.lab2.misc.PumpColor;

import java.util.Objects;

public class PumpImpl extends Pump implements Taggable {
    private String tag;
    public PumpImpl(String name, PumpColor colour, int psi) throws Exception {
        super(name, colour, psi);
        generateTag();
    }

    /**
     * Generate tag based on the Pump Name and Color
     */
    @Override
    public void generateTag() {
        tag = getName().toUpperCase()+"."+getColour().name();
    }

    /**
     * Pump is not for Children
     * @return
     */
    @Override
    public boolean isChildTag() {
        return false;
    }

    /**
     * Returns the tag of Pump
     * @return
     */
    @Override
    public String getTag() {
        return tag;
    }

    // method for comapring 2 objects
    @Override
    public boolean equals(Object o) {
        PumpImpl pump = (PumpImpl) o;
        return super.equals(o) && Objects.equals(getTag(), pump.getTag());
    }

    // hashing code
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getColour(), getPsi(),getTag());
    }

 // returns string repersentation of the obj
    @Override
    public String toString() {
        return "Tag#"+tag+": "+super.toString();
    }
}
