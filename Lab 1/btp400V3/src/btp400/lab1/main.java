package btp400.lab1;

public class main {

		public static void main (String[] args){
			
			 // this will test the Pascal triangle 
			Pascal test = new Pascal (5);
			 
			 System.out.println("Question 1:");
			 System.out.println(test.toString());
			
			// 7 will make it true 
			int matrixTest1[][] = {{ 2, 16, 6 }, { 9, 5, 1 }, { 4, 3, 8 }};
			
			// declaring sqaure matrix
			SquareMatrix test2 = new SquareMatrix(matrixTest1);
			
			// validating test 2 
			System.out.println("Question 2:");
			System.out.println(test2.isSquare());  
			 
			// creating isbn test 
			 ISBN isbnTest1 = new ISBN();
			 
			 System.out.println("Question 3:");
			 
			 // works some how 
			 System.out.println(isbnTest1.verifyISBN("CCCCCCCCCC"));
			 
			 // not a valid isbn (becasue of the two 6's) combo dont equal correct sum
			 System.out.println(isbnTest1.buildISBN("123456679"));
		     
		}
		
	
}
