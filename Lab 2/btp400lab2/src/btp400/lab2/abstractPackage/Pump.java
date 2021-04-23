package btp400.lab2.abstractPackage;

import btp400.lab2.misc.PumpColor;

import java.util.Objects;

public class Pump extends AbstractTaggable {
    private String name;
    private PumpColor colour;
    private Integer psi;

    // setting the variables to the current instance 
    public Pump(String name, PumpColor colour, Integer psi) {
        this.name = name;
        this.colour = colour;
        this.psi = psi;
        generateTag();
    }

    // setters and getters 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PumpColor getColour() {
        return colour;
    }

    public void setColour(PumpColor colour) {
        this.colour = colour;
    }

    public Integer getPsi() {
        return psi;
    }

    public void setPsi(Integer psi) {
        this.psi = psi;
    }

    // method to compare if 2 objects are equal 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pump)) return false;
        Pump pump = (Pump) o;
        return Objects.equals(getName(), pump.getName()) &&
                getColour() == pump.getColour() &&
                Objects.equals(getPsi(), pump.getPsi());
    }

    
    // hashing code
    @Override
    public int hashCode() {

        return Objects.hash(getName(), getColour(), getPsi());
    }

    
    // converting to string
    @Override
    public String toString() {
        return "Pump{" +
                "name='" + name + '\'' +
                ", colour=" + colour +
                ", psi=" + psi +
                '}';
    }

    /**
     * Produce a sequence of digits based on the instance variable
     */
    @Override
    public void generateTag() {
        setTag(getName().toUpperCase()+"."+getColour().name());
    }

    /**
     * Identifies whether object is intended to be for children
     */
    @Override
    public boolean isChildTag() {
        return false;
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
