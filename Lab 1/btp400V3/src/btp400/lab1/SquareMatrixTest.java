// Created By Sahil Patel

package btp400.lab1;

import static org.junit.jupiter.api.Assertions.*;
import btp400.lab1.SquareMatrix; 
import org.junit.jupiter.api.Test;

class SquareMatrixTest {

	@Test
	
	
	void testSquareMatrix() {
	
		// create new 2d-array 
		int[][] arr = new int[2][2];
		
		// create new square matrix using array 
		SquareMatrix sq = new SquareMatrix(arr);
		
		// ensuring array and square matrix are equal 
		assertEquals(arr,sq.getMatrix());
		
		System.out.println(sq.getMatrix());
	
	}

	@Test
	void testIsSquare() {
		int[][] arr = new int[2][2];
		SquareMatrix sq = new SquareMatrix(arr);
		assertEquals(true, sq.isSquare());
		
		System.out.println(sq.isSquare());
	}

}
