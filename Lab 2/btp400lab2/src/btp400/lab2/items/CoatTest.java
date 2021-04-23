package btp400.lab2.items;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import btp400.lab2.misc.Colour;

class CoatTest {

	// testing an large adult coat 
	@Test
	void testConstructorAdultLarge() {
		Size size = Size.L;
		Category category = Category.ADULT;
		Coat coat = new Coat("Canada Goose", new Colour(65, 125, 180), size, category);
		
		assertEquals("Canada Goose", coat.getName());
		
		
		assertEquals(65, coat.getColour().getRed());
		assertEquals(125, coat.getColour().getGreen());
		assertEquals(180, coat.getColour().getBlue());
		
		
		assertEquals("L", coat.getSize().toString());
		
		assertEquals("ADULT", coat.getCategory().toString());
		
	}
	
	// testing a medium adult coat 
	@Test
	void testConstructorAdultMedium() {
		Size size = Size.M;
		Category category = Category.ADULT;
		Coat coat = new Coat("Tommy", new Colour(76, 12, 245), size, category);
		
		assertEquals("Tommy", coat.getName());
		
		
		assertEquals(76, coat.getColour().getRed());
		assertEquals(12, coat.getColour().getGreen());
		assertEquals(245, coat.getColour().getBlue());
		
		
		assertEquals("M", coat.getSize().toString());
		
		assertEquals("ADULT", coat.getCategory().toString());
		
	}
	
	// testing children small coat 
	@Test
	void testConstructorChildrenSmall() {
		Size size = Size.S;
		Category category = Category.CHILDREN;
		Coat coat = new Coat("Ralph Laurence", new Colour(90, 205, 42), size, category);
		
		assertEquals("Ralph Laurence", coat.getName());
		
		
		assertEquals(90, coat.getColour().getRed());
		assertEquals(205, coat.getColour().getGreen());
		assertEquals(42, coat.getColour().getBlue());
		
		
		assertEquals("S", coat.getSize().toString());
		
		assertEquals("CHILDREN", coat.getCategory().toString());
		
	}
	
	// testing the constructor if its bad 
	@Test
	void testConstructorBadArguments() {
		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			Coat garbageCoat = new Coat(null, null, null, null); // Lambda to create bad toy object
		});
		
		String expectedMessage = "Incorrect parameters were set";
		
		String actualMessage = error.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	// testing the conversion of the strings 
	@Test
	void testToString() {
		Size size = Size.S;
		Category category = Category.CHILDREN;
		Coat coat = new Coat("Ralph Laurence", new Colour(90, 205, 42), size, category);
		
		String expectedMessage = "Coat{" +
                "name='" + coat.getName() + '\'' +
                ", colour=" + coat.getColour().toString() +
                ", size=" + coat.getSize().toString() +
                ", category=" + coat.getCategory().toString() +
                '}';
		
		assertEquals(expectedMessage, coat.toString());
	}

}
