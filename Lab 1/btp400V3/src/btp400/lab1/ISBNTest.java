// Created By Sahil Patel

package btp400.lab1;

import static org.junit.jupiter.api.Assertions.*;
import btp400.lab1.ISBN; 
import org.junit.jupiter.api.Test;

class ISBNTest {

	@Test
	// VALID
	void testVerifyISBN() {
		
		// create new ISBN num with specified param
		String isbn = ISBN.buildISBN("123456789");
		
		// check if isbn is valid using the numbers above (INVALID)
		assertEquals(false,ISBN.verifyISBN(isbn));
		
		// check if isbn is valid using number below (VALID)
		assertEquals(true,ISBN.verifyISBN("4837291651"));
		
		// invalid (false)
		System.out.println(ISBN.verifyISBN(isbn));
		
		// valid (true)
		System.out.println(ISBN.verifyISBN("4837291651"));
	}

	@Test
	void testBuildISBN() {
		// create new ISBN num with specified param
		String isbn = ISBN.buildISBN("123456789");
		
		// check is number below equals isbn (VALID)
		assertEquals("1234567891",isbn);
		
		// line 34 is equals to line 37 so its true and working... 
		System.out.println(ISBN.buildISBN("123456789"));
	}

}
