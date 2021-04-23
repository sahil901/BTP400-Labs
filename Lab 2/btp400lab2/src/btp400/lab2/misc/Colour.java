 package btp400.lab2.misc;

import java.util.Objects;

public class Colour {
    private int red;
    private int green;
    private int blue;

    public Colour(int red, int green, int blue) {
    	
    	// if not within the bounds then throw exception 
    	if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255)
    	{
    		throw new IllegalArgumentException("Colour values should be between 0 and 255");
    	}
    	
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    // getters and setters 
    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    // converting to string
    @Override
    public String toString() {
        return "Colour{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

    // checks is obj to equal to another obj 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Colour)) return false;
        Colour colour = (Colour) o;
        return red == colour.red &&
                green == colour.green &&
                blue == colour.blue;
    }

    // hashing the code
    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }
}
