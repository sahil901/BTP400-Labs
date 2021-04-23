// Created By Sahil Patel

package btp400.lab1;

/**
 * Class represent a Square Matrix to check if given matrix is a magic square or
 * not
 *
 * @see SquareMatrix#isSquare()
 *
 */
public class SquareMatrix {

	// declaring the private 2D array
		 private int[][] array;
		 
		 // getter method - return current value of variables (array)
		 public int[][] getMatrix()
		 {
			return array;  
		 }

		    /**
		     * Create the SquareMatrix object with given two-dimensional array of
		     * integers
		     *
		     * @param array a two-dimensional array of integers
		     */
		 
		 	// created the SquareMatrix with a 2D array of integers 
		 // one param constructor 
		    public SquareMatrix(int[][] array) 
		    {
		        this.array = array;
		    }

		    /**
		     * return true if it is a magic square
		     *
		     * @return
		     */
		    
		    // Boolean to check if the square is magic
		    public boolean isSquare() {
		    	
		    	// set default value to true
		        boolean result = true;

		        //check if its a valid array
		        // loop through array to see if its valid 
		        for (int[] array1 : array) 
		        {
		        	// comparing the length of the array1 and array 
		        	// if they are not the same the set to false 
		            if (array1.length != array.length) 
		            {
		                result = false;
		            }
		        }

		        // verify the result but checking if it is a magic square by adding the sum
		        if (result) 
		        {
		        	// set default value of the sum
		            int sum = 0;

		            // iterating through the columns and then...
		            for (int col = 0; col < array.length; col++) 
		            {
		            	// adding the sum into the array
		                sum += array[0][col];
		            }

		            // rows
		            // iterating through the rows and then...
		            for (int row = 1; row < array.length; row++) 
		            {
		            	// set the default value of the newSum
		                int newSum = 0;

		                // every time the row size changes input into the row
		                // iterate through the columns of the current row 
		                for (int col = 0; col < array[row].length; col++) 
		                {
		                	// adding the newSum 
		                    newSum += array[row][col];
		                }

		                // if the sum is not equal to the new sum then set the result to false
		                if (sum != newSum) 
		                {
		                    result = false;
		                }
		            }
		        }

		        // return the result 
		        return result;
		    }
		}
