package btp400.lab1;

//Imported BigInteger - when number gets too high it can't fit into the integer bounds (512)
import java.math.BigInteger;

//Imported ArrayList 
import java.util.ArrayList;


/**
* Class used to store the Pascal's triangle for a given n
*/
// class that hold the pascal code
public class Pascal {

	// Created the variable to hold the pascalTriangle
	// statically allocation of memory 
  private ArrayList<ArrayList<BigInteger>> pascalTriangle = new ArrayList<ArrayList<BigInteger>>();
  
  // Created private variable 
  private int pv;

  /**
   * create the Pascal's triangle for a given n
   *
   * @param n specify the value of n
   */
  
  // Created constructor
  // Receives the n value and then makes the triangle with provided value  
  public Pascal(int n) {
  
  	// Setting the parameter n to current object pv 
      this.pv = n;
      
      // Local Variables
      int i, j;
     
      // Beginning from the first row up until the amount of rows we specify
      for (i = 0; i <= n; i++) 
      {
      	// Created an array list to have the current line that is going to be added
          ArrayList<BigInteger> row = new ArrayList<>();
          
          // Accessing the columns
          for (j = 0; j <= i; j++) 
          {
          	// Using the formula in the calc function for each number getting passed in 
              row.add(calc(i, j));
          }
      
          // Add to the Pascal triangle 
          pascalTriangle.add(row);
      } 
      
  }

  /**
   * calculate and return factorial for given number
   *
   * @param n number to calculate the factorial
   * @return factorial for given number.
   */
  
  // created a function to calculate the factorial 
  public BigInteger factorial(long n) {
      
  	// Initializing the result as 1 (could change this too int result = 1;)
  	BigInteger result = BigInteger.ONE;
  	
  	// increment by one each time until you reach the specified factorial number 
      for (long i = 2; i <= n; i++) 
      {
      	// for each result multiply the result of the factorial
          result = result.multiply(BigInteger.valueOf(i));
      }
      
      // return the calculated factorial result
      return result;
  }

  /**
   * Calculate the N
   *
   * @param n
   * @param r
   * @return
   */
  
  // create a  function to have the formula to create the triangle
  public BigInteger calc(long n, long r) 
  {
  	// Formula for creating the triangle taken from the provided website
	  
	  // returns the resulting calculation 
      return factorial(n).divide(factorial(n - r).multiply(factorial(r)));
  }

  /**
   * Return the string representation of stored Pascal's triangle
   *
   * @return the string representation of stored Pascal's triangle
   */
  @Override
  
  // converting to a string. 
  
 
  public String toString() {
      
	  // creating local variables 
	  int i, j;
	  
	  // creates new string 
      StringBuilder sb = new StringBuilder();
      

      for (i = 0; i <= pv; i++) {
          for (j = 0; j <= pv - i; j++) {
 // responsible for keeping the spacing in the triangle
        	  sb.append(" ");
          }
          
          // gets the pascalTriangle
          ArrayList<BigInteger> row = pascalTriangle.get(i);
         
          // appends spacing and pascal triangle for output 
          for (j = 0; j <= i; j++) {
              sb.append(" ").append(row.get(j).toString());
          }
          
          // end line 
          sb.append("\n");
      }
      
      // return the string
      return sb.toString();
  }

  
  
}
