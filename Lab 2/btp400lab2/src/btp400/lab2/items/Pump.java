package btp400.lab2.items;

import btp400.lab2.misc.PumpColor;

import java.util.Objects;

public class Pump {
    private String name;
    private PumpColor colour;
    private int psi;

    // if paramaters dont match then output the following exception 
    public Pump(String name, PumpColor colour, int psi) {
    	
    	if (name == null || !(name instanceof String) || colour == null || !(colour instanceof PumpColor)) {
    		throw new IllegalArgumentException("Invalid parameters were passed");
    	}
    	
        this.name = name;
        this.colour = colour;
        if(psi<=90){
            this.psi = psi;
        }else {
            throw new IllegalArgumentException("Pump PSI can't be greater than 90");
        }
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

    public int getPsi() {
        return psi;
    }

    // if psi is less than or equal to 90 then set it to current psi else output exception 
    public void setPsi(int psi) throws IllegalArgumentException {
        if(psi<=90){
            this.psi = psi;
        }else {
            throw new IllegalArgumentException("Pump PSI can't be greater than 90");
        }
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

    // conversion to string
    @Override
    public String toString() {
        return "Pump{" +
                "name='" + name + '\'' +
                ", colour=" + colour +
                ", psi=" + psi +
                '}';
    }

}
