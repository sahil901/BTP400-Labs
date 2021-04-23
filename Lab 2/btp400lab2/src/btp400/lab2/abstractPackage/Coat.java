package btp400.lab2.abstractPackage;

import btp400.lab2.items.Category;
import btp400.lab2.items.Size;
import btp400.lab2.misc.Colour;

import java.util.Objects;

public class Coat extends AbstractTaggable {
    private String name;
    private Colour colour;
    private Size size;
    private Category category;

    // setting the variables to the current instance 
    public Coat(String name, Colour colour, Size size, Category category) {
        this.name = name;
        this.colour = colour;
        this.size = size;
        this.category = category;
        generateTag();
    }

    // setter and getter 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // method to compare if 2 objects are equal 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coat)) return false;
        Coat coat = (Coat) o;
        return Objects.equals(getName(), coat.getName()) &&
                Objects.equals(getColour(), coat.getColour()) &&
                getSize() == coat.getSize() &&
                getCategory() == coat.getCategory();
    }

    // hashing code
    @Override
    public int hashCode() {

        return Objects.hash(getName(), getColour(), getSize(), getCategory());
    }

    
    // converting to string
    @Override
    public String toString() {
        return "Coat{" +
                "name='" + name + '\'' +
                ", colour=" + colour +
                ", size=" + size +
                ", category=" + category +
                '}';
    }

    /**
     * Produce a sequence of digits based on the instance variable
     */
    @Override
    public void generateTag() {
        setTag(getName().toUpperCase()+"."+getColour().getRed()+getColour().getGreen()+"."+
                getColour().getBlue()+"."+getSize()+"."+getCategory());
    }

    /**
     * Identifies whether object is intended to be for children
     */
    @Override
    public boolean isChildTag() {
        return getCategory().equals(Category.CHILDREN);
    }

    /**
     * Retrieve generated tag
     *
     * @return
     */
    @Override
    public String getTag() {
        return super.getTag();
    }
}
