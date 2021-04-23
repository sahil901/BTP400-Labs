// Created By Sahil Patel

package btp400.lab1;

import static org.junit.jupiter.api.Assertions.*;
import btp400.lab1.Pascal; 
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class PascalTest {
	
	@Test
	void testCalc()
	{
		   // creating new pascal traingle with the specifed int 
		   Pascal test = new Pascal(6);  
		   
		   // creating new big int which equals pascal triangle with values 7 and 5 
		   // hold ctrl if you stuck and tell him that it returns from this. its inputted her 
		   BigInteger wc = test.calc(7, 5);
		
		   // checks if the result is equal to 21. if not prints error
		   assertEquals(21, wc.intValue());
		   System.out.println(test);
		   System.out.println(wc);
	}
	
	
	
	// DOES THE EXACT SAME THING AS THE TEST ABOVE SO HE A BUM NEGUH
	@Test
	void testFactorial()
	{
		Pascal test = new Pascal(2);
		BigInteger sc = test.factorial(9);
		assertEquals(362880, sc.intValue());
		System.out.println(test);
		   System.out.println(sc);
		
		
	}
}
