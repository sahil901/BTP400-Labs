package btp400.lab2.items;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ToyTest {
	
	// testing the constuctor 
	@Test
	void testToyConstructor() {
		Toy toy = new Toy("Hot Wheels", 5, 10);
		assertEquals(5, toy.getAgeLimitLow());
		assertEquals("Hot Wheels", toy.getName());
		assertEquals(10, toy.getAgeLimitHigh());
		
		
		Toy toy2 = new Toy(); // Empty
		assertEquals(null, toy2.getName());
		assertEquals(0, toy2.getAgeLimitLow());
		assertEquals(0, toy2.getAgeLimitHigh());
		
	}
	
	// passing bad value to ensure the check works for it failing 
	@Test
	void testToyConstructorBad() {	
		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			Toy toy3 = new Toy(null, -1, -1); // Lambda to create bad toy object
		});
		
		String expectedMessage = "Incorrect parameters were set";
		
		String actualMessage = error.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	// testing the conversion to string
	@Test
	void testToString() {
		Toy toy = new Toy("Hot Wheels", 5, 10);
		
		String expectedMessage = "Toy{" +
                "name='" + toy.getName() + '\'' +
                ", ageLimitLow=" + toy.getAgeLimitLow() +
                ", ageLimitHigh=" + toy.getAgeLimitHigh() +
                '}';
		
		assertEquals(expectedMessage, toy.toString());
	}

}
