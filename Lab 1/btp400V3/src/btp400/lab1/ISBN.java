// Created By Sahil Patel

package btp400.lab1;

/**
*
* ISBN class has to static method to generate and verify the ISBN 10 number
*/
public class ISBN {

	/**
     * Method to verify the ISBN 10
     *
     * @param number ISBN 10 number without spaces and dashes '-'
     * @return true if given number is a valid ISBN 10 number
     */
	
	// Accepts ISBN and returns if number is valid or not 
    public static boolean verifyISBN(String number) {

        // declare variables
        int sum = 0; // for sum 
        int digit; // for number
        char ch; // for X

        // add up to 9th digit
        for (int i = 1; i <= 9; i++) 
        {
        	// Returns the char value at the specified index  
        	// setting every number from param into ch 
            ch = number.charAt(i - 1);
            
            // getting the numeric value of the character ch variable 
            digit = Character.getNumericValue(ch);
            
            // repeatedly adds to sum 
            sum += (i * digit);
        }

        // last digit
     // Returns the char value at index 9 
        ch = number.charAt(9);
        
        // Converts the character to uppercase 
        ch = Character.toUpperCase(ch);
       
        // if the character is an uppercase X then do the following... 
        if (ch == 'X') 
        {
        	// 100 
            sum += (10 * 10);
        } 
        
        // else do the following...
        else 
        {
        	// set numeric value of ch to digit  
            digit = Character.getNumericValue(ch);
            
            // add digit mutiplied by 10 to sum
            sum += (digit * 10);
        }
        // check sum

        // returns if sum is divided by 11 and has no remainder (bool)
        return sum % 11 == 0;
    }

    /**
     * Return the associated valid ISDN number
     *
     * @param number ISBN number having 9 digits
     * @return associated valid ISDN of given number
     * @throws java.lang.Exception throw exception if the given number has more
     * than 9 characters
     */
    
    // Function that will take the 9 digits and then add the 10th digit. 
    public static String buildISBN(String number) {       
    	
        // declare variables
        int sum = 0;
        int digit; // for number
        char ch; // for X

        // add up to 9th digit
        for (int i = 1; i <= 9; i++) 
        {
        	// Returns the char value at the specified index.
            ch = number.charAt(i - 1);
            
            // getting the numeric value of the character ch variable 
            digit = Character.getNumericValue(ch);
            
            // add the numeric value of the character to the sum 
            sum += (digit);
        }

        // creating the ISBN
        String ISBN;
        
        // 9th num is the reminder of sum divided by 11 
        int d9 = sum % 11;
        
        //check the condition
        
        // If ninth digit is less than 10 then ISBN is number added to the 9th digit (VALID)
        if (d9 < 10) 
        {
            ISBN = number + d9;
        } 
        
        // otherwise d9 should be set to the character digit "X". (INVALID)
        else 
        {
            ISBN = number + "X";
        }

        // return isbn
        return ISBN;
        
 
    }

}
