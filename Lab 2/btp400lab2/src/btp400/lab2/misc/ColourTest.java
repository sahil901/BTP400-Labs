package btp400.lab2.misc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import btp400.lab2.items.Toy;

class ColourTest {

	// testing the constructor to ensure that the values are between 0 and 255
	@Test
	void testConstructor() {
		Colour goodColour = new Colour(90, 205, 42);
		
		assertEquals(90, goodColour.getRed());
		assertEquals(205, goodColour.getGreen());
		assertEquals(42, goodColour.getBlue());
	}
	
	// testing the constructor with bad values so it will output exception since numbers are not in bounds
	@Test
	void testBadConstructor() {
		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			Colour badColour = new Colour(-25, 567, 1000); // Lambda to create bad toy object
		});
		
		String expectedMessage = "Colour values should be between 0 and 255";
		
		String actualMessage = error.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	// testing to ensure the conversion was done properly 
	@Test 
	void testToString() {
		
		Colour colourToTest = new Colour(90, 205, 42);
		
		String expectedMessage = "Colour{" +
                "red=" + colourToTest.getRed() +
                ", green=" + colourToTest.getGreen() +
                ", blue=" + colourToTest.getBlue() +
                '}';
	}

}
