package btp400.lab4;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to model a grade
 * <p>A grade could be A+ through F</p>
 * @author Sahil Patel
 *
 */
enum Grade {A_PLUS {
	@Override
	public String toString() {
		return "A+";
	}
}, A {
	@Override
	public String toString() {
		return "A";
	}
} , B_PLUS {
	@Override
	public String toString() {
		return "B+";
	}
	}, B {
	@Override
	public String toString() {
		return "B";
	}
}, C_PLUS {
	@Override
	public String toString() {
		return "C+";
	}
}, C {
	@Override
	public String toString() {
		return "C";
	}
}, D_PLUS {
	@Override
	public String toString() {
		return "D+";
	}
}, D {
	@Override
	public String toString() {
		return "D";
	}
}, F {
	@Override
	public String toString() {
		return "F";
	}
}}

/**
 * A class to model a term. A term could be Summer, Winter or Fall
 */
enum Term {Winter, Summer, Fall}

/**
 * 
 * Sample btp400.lab4.Student class for BTP400 @ Seneca College.  Used
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
	//the DatabaseHandler instance
	private static final DatabaseHandler dbHandler = new DatabaseHandler();
	//the Connection to the database
	private static Connection connection;
	private List<Result> results = new ArrayList();

	/**
	 * Create the tables once the student class is instantiated
	 */
	public Student() {
		try {
			connection = dbHandler.db();
		} catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
		}
	}
	
	/**
	 * 
	 * Create a new student.
	 * 
	 * @param nm - name 
	 * @param num - student number
	 */
	Student (String nm, int num) {
//		System.setSecurityManager(new SecurityManager());
		if (nm.isBlank()) 
			throw new IllegalArgumentException("course code cannot be blank.");
		studentName = nm;
		studentNumber = num;
		results = new ArrayList<Result>();
	}
	
	/**
	 * Constructs a Student object and attempts to log in and retrieve a student's results
	 * @param num the student number whose results to retrieve
	 * @param userId the id of the user attempting to log in
	 * @param password the password of the user attempting to log in
	 */
	Student(int num, String userId, String password) {
//		System.setSecurityManager(new SecurityManager());
		//if the student successfully logs in, obtain their results
		try {
			if(login(userId, password)) {
				System.out.println("Successfuly logged in");
				getResults(num);
			}
			//otherwise notify the student of an incorrect password
			else
				System.out.println("Your password is incorrect.");
		} catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());;
		}
		
	}

	/**
	 * Logs a user in
	 * <p>Compares the entered password and stored password for a userId.</p>
	 * @param userId the User id for the user attempting to log in
	 * @param password the password for the user attempting to log in
	 * @return true on login success or false on login failure
	 * @throws Exception if a connection to the database could not be established
	 */
	public boolean login(String userId, String password) throws Exception {
		final String RETRIEVAL_QUERY = "SELECT user_password FROM User WHERE user_id = ?;";
		connection = dbHandler.db();
		PreparedStatement pStatement = connection.prepareStatement(RETRIEVAL_QUERY);
		pStatement.setString(1, userId);
		ResultSet passwordRs = pStatement.executeQuery();
		if(passwordRs.next()) {
            String retrievedPassword = passwordRs.getString("user_password");
            connection.close();
            if(encrypt(password).equalsIgnoreCase(retrievedPassword)) {
                return true;
            }
        }
        connection.close();
		return false;
	}

	/**
	 * Adds adds a new result to the list of results
	 *
	 * @param c the course associated with the result
	 * @param t the term associated with the result
	 * @param y the year of the result
	 * @param f the grade of the result
	 * @return true on success or false on failure
	 */
	public boolean add(String c, Term t, int y, Grade f) {
		try {
			results.add(new Result(c,t,y,f));
		} catch(Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Persists the student's results to the database
	 */
	public void save() {
		final String INSERTION_QUERY = "INSERT INTO Result(student_id, course_name, letter_grade, term_id, year) " +
				"VALUES(?, ?, ?, ?, ?);";
		try {
			connection = dbHandler.db();
			PreparedStatement pStatement = connection.prepareStatement(INSERTION_QUERY);
			for(Result result: results) {
				pStatement.setInt(1, studentNumber);
				pStatement.setString(2, result.getCourseCode());
				pStatement.setString(3, result.getFinalGrade().toString());
				pStatement.setInt(4, getTermId(result.getTerm()));
				pStatement.setInt(5, result.getYear());
				System.out.println(pStatement.execute());
			}
			connection.close(); //close the connection once done using the DB
		}catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
		}


	}

    /**
     * Retrieves the ID associated with a term from the term table in the database
     * @param term the term whose ID is to be obtained
     * @return the term ID, or -1 if the term isn't in the database
     */
	private int getTermId(Term term) {
        final String RETRIEVAL_QUERY = "SELECT term_id FROM Term WHERE term_name = ?;";
        try {
            connection = dbHandler.db();
            PreparedStatement pStatement = connection.prepareStatement(RETRIEVAL_QUERY);
            pStatement.setString(1, term.toString());
            ResultSet termIdRs = pStatement.executeQuery();
            if(termIdRs.next()) {
                int retrievedTermId = termIdRs.getInt("term_id");
                connection.close();
                    return retrievedTermId;
            }
            connection.close();
            return -1;
        }catch(Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
        }
        return -1;

    }

	/**
	 * Retrieves the results for the student with student number num
	 *
	 * @param num
	 * @throws Exception if a connection to the database could not be established
	 */
	private void getResults(int num) throws Exception {
		final String RETRIEVAL_QUERY = "SELECT * FROM result WHERE student_id = ?;";
		final String TERM_QUERY = "SELECT term.term_name FROM term LEFT JOIN result ON term.term_id = result.term_id;";
		connection = dbHandler.db();

		PreparedStatement pStatement = connection.prepareStatement(RETRIEVAL_QUERY);
		pStatement.setInt(1, num);

		ResultSet retrievedResults = pStatement.executeQuery(RETRIEVAL_QUERY);
		ResultSet termRs = pStatement.executeQuery(TERM_QUERY);

		while(retrievedResults.next()) {
			System.out.println(retrievedResults.toString());
			String term_name = termRs.getString("term_name");
			Term term = null;
			if(term_name.equals("Fall"))
				term = Term.Fall;
			if(term_name.equals("Summer"))
				term = Term.Winter;
			if(term_name.equals("Summer"))
				term = Term.Summer;

			String retrievedGrade = retrievedResults.getString("letter_grade");
			Grade grade = null;
			switch(retrievedGrade) {
				case "A+": grade = Grade.A_PLUS; break;
				case "A": grade = Grade.A; break;
				case "B+": grade = Grade.B_PLUS; break;
				case "B": grade = Grade.B; break;
				case "C+": grade = Grade.C_PLUS; break;
				case "C": grade = Grade.C; break;
				case "D+": grade = Grade.D_PLUS; break;
				case "D": grade = Grade.D; break;
				case "F": grade = Grade.F; break;
			}

			results.add(new Result(
					retrievedResults.getString("course_name"),
					term,
					Integer.parseInt(retrievedResults.getString("year")),
					grade
			));
			retrievedResults.close();
			termRs.close();
		}
		connection.close(); //close the connection once done using the DB
	}
	
	
	/**
	 * Obtains the student GPA based on all the courses taken.
	 *
	 * @return the average GPA or zero if the student's results have not been added
	 */
	public double getGPA() {
        final double[] totalGpa = {0};
        results.stream().forEach((result) -> {
            Grade finalGrade = result.getFinalGrade();
            switch (finalGrade) {
                    case A_PLUS:
                        totalGpa[0] += getGPAValue("A+");
                        break;
                    case A:
                        totalGpa[0] += getGPAValue("A");
                        break;
                    case B_PLUS:
                        totalGpa[0] += getGPAValue("B+");
                        break;
                    case B:
                        totalGpa[0] += getGPAValue("B");
                        break;
                    case C_PLUS:
                        totalGpa[0] += getGPAValue("C+");
                        break;
                    case C:
                        totalGpa[0] += getGPAValue("C");
                        break;
                    case D_PLUS:
                        totalGpa[0] += getGPAValue("D+");
                        break;
                    case D:
                        totalGpa[0] += getGPAValue("D");
                        break;
                    case F:
                        totalGpa[0] += getGPAValue("F");
                        break;
                }
        });
        if(results.size() > 0)
			return totalGpa[0] / results.size(); //return the average gpa
        return 0;
	}

	/**
	 * Retrieves the description associated with a course
	 * @param courseCode
	 * @return the course description
	 */
	private String getCourseDescription(String courseCode) {
		final String RETRIEVAL_QUERY = "SELECT course_description FROM course WHERE course_name = ?;";
		try {
			connection = dbHandler.db();
			PreparedStatement pStatement = connection.prepareStatement(RETRIEVAL_QUERY);
			pStatement.setString(1, courseCode);
			ResultSet courseDescriptionRs = pStatement.executeQuery();
			courseDescriptionRs.next();
			String courseDescription = courseDescriptionRs.getString("course_description");
			connection.close();
			return courseDescription;
		}catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
		}
		return null;
	}

	/**
	 * Retrieves the GPA value associated with a letter grade from the database
	 * @param letterGrade the letter grade to retrieve the GPA value for
	 * @return the GPA value associated with a letter grade
	 */
	private double getGPAValue(String letterGrade) {
        final String RETRIEVAL_QUERY = "SELECT GPA_value FROM GPA WHERE letter_grade = ?;";
        List<Result> results = new ArrayList<>();
        try {
			connection = dbHandler.db();
			PreparedStatement pStatement = connection.prepareStatement(RETRIEVAL_QUERY);
			pStatement.setString(1, letterGrade);
			ResultSet gpaValueRs = pStatement.executeQuery();
			gpaValueRs.next();
			double gpaValue = gpaValueRs.getDouble("GPA_value");
			connection.close();
			return gpaValue;

		}catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
		}
       return 0;
    }
	
	/**
	 * Return the GPA for a given class.  The class can be uniquely identified by the
	 * course code, year and term.
	 * 
	 * <p>The list of students is passed in as a parameter.</p>
	 * 
	 * @param courseCode - course code representing course
	 * @param t - term class took place in
	 * @param y - Year class occurred 
	 * @param studentSet - A set of students (who, may or may not be in that class)
	 * @return the average GPA for a given class or zero if the students are not in the class or the set is empty
	 */
	public static double getClassGPA(String courseCode, Term t, int y, Set<Student> studentSet) {
	    final double[] totalClassGPA = {0};
		studentSet.stream().forEach((student) -> {
                totalClassGPA[0] += student.getGPA();
        });
		if(studentSet.size() > 0)
			return totalClassGPA[0] / studentSet.size(); //return the class GPA
		return 0; //return 0 if the set of students is empty
	}
		

	/**
	 * print student results onto console. It shall be in the following format
	 * [btp400.lab4.Student ID, btp400.lab4.Student Name, Course Code, Course Description] with each result
	 * on a different line.
	 *
	 */
	public void printResult() {
		try{
			System.out.println("Student number " + studentNumber);
			getResults(studentNumber);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
		}
		results.forEach((result) -> {
			System.out.println("[" + studentNumber + ", " + studentName + ", " + result.getCourseCode() + ", " +
					getCourseDescription(result.getCourseCode()) + ", " + result.getTerm() + "]\n");
		});
	}

	/**
	 * Encrypts a string using the AES encryption algorithm
	 * @param string the string to be encrypted
	 * @return an encrypted string or null if encryption fails for some reason
	 */
	private static String encrypt(String string) {
		final String KEY = "aesEncryptionKey";
		try {
			byte[] keyBytes = Arrays.copyOf(KEY.getBytes("ASCII"), 16);

			SecretKey key = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] cleartext = string.getBytes("UTF-8");
			byte[] ciphertextBytes = cipher.doFinal(cleartext);

			return new String(Hex.encodeHex(ciphertextBytes));

		} catch (UnsupportedEncodingException | NoSuchAlgorithmException
				| NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
		}
		return null;
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
		/** final btp400.lab4.Grade obtained by student in course */
		private Grade finalGrade;		
		
		/**
		 * 
		 * @param c course code
		 * @param t term
		 * @param y year
		 * @param f final btp400.lab4.Grade
		 */
		private Result(String c, Term t, int y, Grade f) {	
			if (c.isBlank()) throw new IllegalArgumentException("course code cannot be blank.");
			term = t;
			year = y;
			courseCode = c;
			finalGrade = f; 		
		}
		
		public String getCourseCode() {return courseCode;}	
		public Grade getFinalGrade() {return finalGrade;}
		public Term getTerm() {return term; }
		public int getYear() {return year; }
		
	
	}
	
	


}
