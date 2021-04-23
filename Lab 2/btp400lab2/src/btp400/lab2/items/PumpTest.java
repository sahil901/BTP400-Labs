package btp400.lab2.items;

import static org.junit.jupiter.api.Assertions.*;
import btp400.lab2.misc.PumpColor;

import org.junit.jupiter.api.Test;

class PumpTest {

	// testing the constructor 
	@Test
	void testConstructor() {
		
		PumpColor blackPump = PumpColor.BLACK;
		
		Pump pump = new Pump("Working pump", blackPump, 16);
		
		assertEquals("Working pump", pump.getName());
		assertEquals("BLACK", pump.getColour().toString());
		assertEquals(16, pump.getPsi());
	}

	// for testing the bad pump and colour 
	@Test
	void testConstructorBadNameAndColour() {
		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			Pump pump = new Pump(null, null, 16); // Lambda to create bad toy object
		});
		
		String expectedMessage = "Invalid parameters were passed";
		
		String actualMessage = error.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	// test the psi for being bad since its over 90 
	@Test
	void testConstructorBadPSI() {
		
		PumpColor blackPump = PumpColor.BLACK;
		
		Exception error = assertThrows(IllegalArgumentException.class, () -> {
			Pump pump = new Pump("Bad Pump", blackPump, 190); // Lambda to create bad toy object
		});
		
		String expectedMessage = "Pump PSI can't be greater than 90";
		
		String actualMessage = error.getMessage();
		
		assertEquals(expectedMessage, actualMessage);
	}
	
	// for teesting psi and making sure its valid 
	@Test
	void testSetPSI() {
		PumpColor blackPump = PumpColor.BLACK;
		
		Pump pump = new Pump("Working pump", blackPump, 16);
		
		try {
			pump.setPsi(25);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		
		assertEquals(25, pump.getPsi());
		
	}
	
	// making sure the psi is not over 90
	@Test
	void testSetPSIFail() {
		PumpColor blackPump = PumpColor.BLACK;
		
		Pump pump = new Pump("Working pump", blackPump, 16);
		
		String errorMessage = new String();
		
		try {
			pump.setPsi(250);
		} catch (IllegalArgumentException e) {
			errorMessage = e.getMessage();
		}
		
		String expectedMessage = "Pump PSI can't be greater than 90";
		
		assertEquals(expectedMessage, errorMessage);
		
	}
	
	
	// conversion to string
	@Test
	void testToString() {
		PumpColor whitePump = PumpColor.WHITE;
		
		Pump pump = new Pump("Working pump", whitePump, 60);
		
		String expectedMessage = "Pump{" +
                "name='" + pump.getName() + '\'' +
                ", colour=" + pump.getColour().toString() +
                ", psi=" + pump.getPsi() +
                '}';
		
		assertEquals(expectedMessage, pump.toString());
	}
}
