package btp400.lab2.abstractPackage;

import java.util.Objects;

public class Toy extends AbstractTaggable {
    private String name;
    private Integer ageLimitLow;
    private Integer ageLimitHigh;

    /**
     *
     * @param name
     * @param ageLimitLow
     * @param ageLimitHigh
     */
    public Toy(String name, Integer ageLimitLow, Integer ageLimitHigh) {
        this.name = name;
        this.ageLimitLow = ageLimitLow;
        this.ageLimitHigh = ageLimitHigh;
        generateTag();
    }

    // setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAgeLimitLow() {
        return ageLimitLow;
    }

    public void setAgeLimitLow(Integer ageLimitLow) {
        this.ageLimitLow = ageLimitLow;
    }

    public Integer getAgeLimitHigh() {
        return ageLimitHigh;
    }

    public void setAgeLimitHigh(Integer ageLimitHigh) {
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

    /**
     * Produce a sequence of digits based on the instance variable
     */
    @Override
    public void generateTag() {
        setTag(getName().toUpperCase()+"."+getAgeLimitLow()+"."+getAgeLimitHigh());
    }

    /**
     * Identifies whether object is intended to be for children
     */
    @Override
    public boolean isChildTag() {
        return true;
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
