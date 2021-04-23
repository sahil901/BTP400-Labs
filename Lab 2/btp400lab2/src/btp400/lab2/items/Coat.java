package btp400.lab2.items;

import btp400.lab2.misc.Colour;

import java.util.Objects;

public class Coat {
    private String name;
    private Colour colour;
    private Size size;
    private Category category;

    /**
     * Constructor
     * @param name
     * @param colour
     * @param size
     * @param category
     */
    
    // if paramaters dont match then output the following exception 
    public Coat(String name, Colour colour, Size size, Category category) {
    	
    	if (name == null || !(name instanceof String) || colour == null || !(colour instanceof Colour) || size == null || !(size instanceof Size) || category == null || !(category instanceof Category))
    	{
    		throw new IllegalArgumentException("Incorrect parameters were set");
    	}
    	
        this.name = name;
        this.colour = colour;
        this.size = size;
        this.category = category;
    }

    // setters and getters 
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

    // hashing done
    @Override
    public int hashCode() {

        return Objects.hash(getName(), getColour(), getSize(), getCategory());
    }

    // convering to string 
    @Override
    public String toString() {
        return "Coat{" +
                "name='" + name + '\'' +
                ", colour=" + colour +
                ", size=" + size +
                ", category=" + category +
                '}';
    }
}
