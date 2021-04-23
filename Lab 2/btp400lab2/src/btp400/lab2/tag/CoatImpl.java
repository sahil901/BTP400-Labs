package btp400.lab2.tag;

import btp400.lab2.items.Category;
import btp400.lab2.items.Coat;
import btp400.lab2.items.Size;
import btp400.lab2.misc.Colour;

import java.util.Objects;

public class CoatImpl extends Coat implements Taggable {
    private String tag;

    public CoatImpl(String name, Colour colour, Size size, Category category) {
        super(name, colour, size, category);
        generateTag();
    }

    /**
     * Generation of Tag based on it's property like Name, Colour, Size etc. If all the property is
     * same then the Random sequence generator will make difference to it.
     */
    @Override
    public void generateTag() {
        tag = getName().toUpperCase()+"."+getColour().getRed()+"."+getColour().getGreen()+"."+
                getColour().getBlue()+"."+getSize()+"."+getCategory();
    }

    /**
     * Returns true if it is for child else returns false
     * @return
     */
    @Override
    public boolean isChildTag() {
        return getCategory().equals(Category.CHILDREN);
    }

    /**
     * Returns the tag of the item
     * @return
     */
    @Override
    public String getTag() {
        return tag;
    }

    /**
     * Tags also need be compared like other properties
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        CoatImpl coatImpl = (CoatImpl) o;
        return super.equals(o) && Objects.equals(getTag(), coatImpl.getTag());
    }

    // hashing code 
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getColour(), getSize(), getCategory(),getTag());
    }

    // returns string repersentation of the obj
    @Override
    public String toString() {
        return "Tag#"+tag+": "+super.toString();
    }
}
