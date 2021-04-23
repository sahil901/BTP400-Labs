
import java.util.List;
import java.util.Set;

enum Grade {A_PLUS, A, B_PLUS, B, C_PLUS, C, D_PLUS, D, F};
enum Term {Fall, Winter, Summer};


/**
 * 
 * Sample Student class for BTP400 @ Seneca College.  Used
 * as a starting point for Winter 2021 Lab 4.
 * 
 * 
 * 
 * @author EDEN.BURTON
 *
 */
public class Student {
	private int studentNumber;
	private String studentName;
	List<Result> results;
	
	
	/**
	 * 
	 * Create a new student.
	 * 
	 * @param nm - name 
	 * @param num - student number
	 * 
	 */
	Student (String nm, int num, String userId, String password) { 
		if (nm.isBlank()) 
			throw new IllegalArgumentException("student name cannot be blank.");
		studentName = nm;
		studentNumber = num;
		
		// authenticate user...throw exception if invalid user
	}
	
	/**
	 * 
	 * 
	 * Retrieve results for student identified by student number 
	 * 
	 * @param num - student number to be retrieved from the DB
	 * 
	 * 
	 */
	Student(int num, String userId, String password) {
		
		// authenticate user...throw exception if invalid user
		
		// retrieve results
		
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean login(String userId, String password) {
		
		// get the encrpyt key
		// encrpyt password
		// retrieve password from DB
		// check for equality		
		return true;
	}
	
	public boolean add(String c, Term t, int y, Grade f) {
		try {
			results.add(new Result(c,t,y,f));
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * return the student GPA based on all the courses taken.
	 * 
	 * @return
	 */
	public double getGPA() {
			
		return 0;		
	}
	
	/**
	 * Return the GPA for a given class.  The class can be uniquely identified by the
	 * course code, year and term.
	 * 
	 * The list of students is passed in as a parameter.
	 * 
	 * @param courseCode - course code representing course
	 * @param t - term class took place in
	 * @param y - Year class occurred 
	 * @param studentSet - A set of students (who, may or may not be in that class)
	 * @return
	 */
	public static double getClassGPA(String courseCode, Term t, int y, Set<Student> studentSet) {
		return 0;		
	}
		

	/**
	 * print student results onto console. It shall be in the following format
	 * [Student ID, Student Name, Course Code, Course Description] with each result
	 * on a different line.
	 * 
	 * @return
	 */
	public void printResult() {
		
	}	
	
	
	
	/**
	 * 
	 * persist results into the data store
	 * 
	 */
	public void save() {
		
	}
	
	
	/**
	 * 
	 *This is an inner class that stores final grade of a individual student
	 */		
	private class Result {

		/** identify course the grade pertains to */
		private String courseCode;
		/** term student took the course */
		private Term term;
		/** year student took the course  */
		private int year;
		/** final Grade obtained by student in course */
		private Grade finalGrade;		
		
		/**
		 * 
		 * @param c course code
		 * @param t term
		 * @param y year
		 * @param f final Grade
		 */
		private Result(String c, Term t, int y, Grade f) {	
			if (c.isBlank()) throw new IllegalArgumentException("course code cannot be blank.");
			courseCode = c;
			finalGrade = f; 		
		}
		
		public String getCourseCode() {return courseCode;}	
		public Grade getFinalGrade() {return finalGrade;}
		public Term getTerm() {return term; }
		public int getYear() {return year; }
		
	
	}	
	
}
