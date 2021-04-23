package btp400.lab2.items;

import java.util.Objects;

public class Toy {
    private String name;
    private int ageLimitLow;
    private int ageLimitHigh;

    /**
     * Constructor
     * @param name
     * @param ageLimitLow
     * @param ageLimitHigh
     */
    
    // setting the age between 5 to 12 (assumption)
    public Toy(String name, int ageLimitLow, int ageLimitHigh) {
    	
    	// A child should be between the ages of 5 and 12
    	if (name == null || (ageLimitLow <= 0 || ageLimitLow < 5) || (ageLimitHigh > 12 || ageLimitHigh < ageLimitLow)) 
    	{
    		throw new IllegalArgumentException("Incorrect parameters were set");
    	}
    	
        this.name = name;
        this.ageLimitLow = ageLimitLow;
        this.ageLimitHigh = ageLimitHigh;
    }
    
    public Toy() {
    	this.name = null;
    	this.ageLimitLow = 0;
    	this.ageLimitHigh = 0;
    }

    // setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeLimitLow() {
        return ageLimitLow;
    }

    public void setAgeLimitLow(int ageLimitLow) {
        this.ageLimitLow = ageLimitLow;
    }

    public int getAgeLimitHigh() {
        return ageLimitHigh;
    }

    public void setAgeLimitHigh(int ageLimitHigh) {
        this.ageLimitHigh = ageLimitHigh;
    }

    // method to compare if 2 objects are equal 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toy)) return false;
        Toy toy = (Toy) o;
        return getAgeLimitLow() == toy.getAgeLimitLow() &&
                getAgeLimitHigh() == toy.getAgeLimitHigh() &&
                Objects.equals(getName(), toy.getName());
    }

    
    // hashing code
    @Override
    public int hashCode() {

        return Objects.hash(getName(), getAgeLimitLow(), getAgeLimitHigh());
    }

    
    // converting to string
    @Override
    public String toString() {
        return "Toy{" +
                "name='" + name + '\'' +
                ", ageLimitLow=" + ageLimitLow +
                ", ageLimitHigh=" + ageLimitHigh +
                '}';
    }
}
