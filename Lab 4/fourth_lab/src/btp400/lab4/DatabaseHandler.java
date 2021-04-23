	package btp400.lab4;

	import java.security.AllPermission;

/*
 	* To change this license header, choose License Headers in Project Properties.
 	* To change this template file, choose Tools | Templates
 	* and open the template in the editor.
 	*/

	// including necessary libraries
	import java.sql.*;
	import java.util.logging.Level;
	import java.util.logging.Logger;


  /**
 	* This class is a helper class for connecting to the database
 	*/
public class DatabaseHandler {

	/*
	 * A connection (session) with a specific database. SQL statements are executed and results are returned within the context of a connection. 
	 * The object used for executing a static SQL exe_stat and returning the results it produces.
	 * A table of data representing a database result set, which is usually generated by executing a exe_stat that queries the database.
	 * Comprehensive information about the database as a whole. 
	 */
	private static Connection new_session = null;
	private Statement exe_stat = null;
	private ResultSet data_table = null;
	public DatabaseMetaData db_data = null;

	/*
	 *  Database credentials:
	 *  the URL to the database in the form jdbc:mysql://hostname:port/databasename
	 *  UserName of DB
	 *  Password of DB
	 */
	final private String DB_URL = "jdbc:mysql://mymysql.senecacollege.ca/db_spatel392";
	final private String USER_NAME = "db_spatel392";
	final private String PASSWORD = ")2vMnN[zt9";

	 /**
	   * Connects to the database and attempts to create tables
	   * @return connection to the database
	   * @throws Exception when a connection to the database cannot be established
	   */
	public Connection db() throws Exception 
	{
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager != null) {
			securityManager.checkPermission(new AllPermission());
		}
		
		try 
		{
			/*
			 * Loading the JDBC Driver 
			 * Output to console 
			 * Setting up connection to the DB
			 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connecting to database...");
			new_session = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("Connected to database...");
			
			/*
			 * Grab data and see if the user table is already in the db
			 */
			DatabaseMetaData db_data = new_session.getMetaData();
			exe_stat = new_session.createStatement();
			data_table = db_data.getTables(null, null, "User", null);
			
			/*
			 * if the user table does not exist then make all 5 tables and populate 3 of them (Creating the DB Script)
			 * 5 tables: Student, Course, GPA, User, and Term
			 * Populate: User, GPA and Course
			 */
			if (!data_table.next()) 
			{
				/*
				 * Create the Student table inside of the DB so we can populate the rows
				 * Update the student table
				 */
				System.out.println("Creating Student Table...");
				String student_table = "CREATE TABLE db_spatel392.Student (\r\n" + "student_id int NOT NULL,\r\n" + "	student_name varchar(255) NOT NULL,\r\n" + "        CONSTRAINT pkStudent PRIMARY KEY (student_id)\r\n" + ");";
				exe_stat.executeUpdate(student_table);
				System.out.println("Student Table created successfully...");

				/*
				 * Create the course table inside of the DB so we can populate the rows
				 * Update the course table
				 */
				System.out.println("Creating Course Table...");
				String course_table = "CREATE TABLE db_spatel392.Course (\r\n" + "	course_name varchar(255) NOT NULL,\r\n" + "	course_description varchar(255) NOT NULL,\r\n" + "	CONSTRAINT pkCourse PRIMARY KEY (course_name)	\r\n" + "\r\n" + ");";
				exe_stat.executeUpdate(course_table);
				System.out.println("Course Table created successfully...");

				/*
				 * Create a new row in the db for each course in the following format (courseName, course desc)
				 * Update the row each time a new course is added
				 */
				String add_course = "insert into Course values('BAB140', 'Introduction to Financial Accounting');";
				exe_stat.executeUpdate(add_course);
				add_course = "insert into Course values('BTC140', 'Critical Thinking and Writing');";
				exe_stat.executeUpdate(add_course);
				add_course = "insert into Course values('BTO120', 'Operating Systems for Programmers - UNIX');";
				exe_stat.executeUpdate(add_course);
				add_course = "insert into Course values('BTP100', 'Programming Fundamentals Using C');";
				exe_stat.executeUpdate(add_course);
				add_course = "insert into Course values('BTP105', 'Computer Principles for Programmers');";
				exe_stat.executeUpdate(add_course);

				/*
				 * Create the GPA table inside of the DB so we can populate the rows
				 * Update the GPA table
				 */
				System.out.println("Creating GPA Table...");
				String gpa_table = "CREATE TABLE db_spatel392.GPA (\r\n" + "	letter_grade varchar(255) NOT NULL,\r\n" + "	GPA_value double NOT NULL,\r\n" + "	CONSTRAINT pkGPA PRIMARY KEY (letter_grade)\r\n" + "\r\n" + ");";
				exe_stat.executeUpdate(gpa_table);
				System.out.println("GPA Table created successfully...");

				/*
				 * Create a new row in the db for each value in the following format (letterGrade, number grade)
				 * Update the row each time a new GPA is added
				 */
				String add_gpa = "insert into GPA values('A+', 4.0);";
				exe_stat.executeUpdate(add_gpa);
				add_gpa = "insert into GPA values('A', 4.0);\r\n";
				exe_stat.executeUpdate(add_gpa);
				add_gpa = "insert into GPA values('B+', 3.5);\r\n";
				exe_stat.executeUpdate(add_gpa);
				add_gpa = "insert into GPA values('B', 3.0);\r\n";
				exe_stat.executeUpdate(add_gpa);
				add_gpa = "insert into GPA values('C+', 2.5);\r\n";
				exe_stat.executeUpdate(add_gpa);
				add_gpa = "insert into GPA values('C', 2.0);\r\n";
				exe_stat.executeUpdate(add_gpa);
				add_gpa = "insert into GPA values('D+', 1.5);\r\n";
				exe_stat.executeUpdate(add_gpa);
				add_gpa = "insert into GPA values('D', 1.0);\r\n";
				exe_stat.executeUpdate(add_gpa);
				add_gpa = "insert into GPA values('F', 0.0);";
				exe_stat.executeUpdate(add_gpa);

				/*
				 * Create the User table inside of the DB so we can populate the rows
				 * Update the user table
				 */
				System.out.println("Creating User Table...");
				String user_table = "CREATE TABLE db_spatel392.User (\r\n" + "	user_id int NOT NULL,\r\n" + "	user_password varchar(255) NOT NULL,\r\n" + "	CONSTRAINT pkUser PRIMARY KEY (user_id)\r\n" + "	\r\n" + ");";
				exe_stat.executeUpdate(user_table);
				System.out.println("User Table created successfully...");

				/*
				 * Create a new row in the db for each value in the following format (userid, password)
				 * Update the row each time a new user is added
				 */
				String add_user = "INSERT INTO User VALUES(1, Hex(aes_encrypt('Sahil', 'aesEncryptionKey')));\n";
				exe_stat.executeUpdate(add_user);

				add_user = "INSERT INTO User VALUES(2, Hex(aes_encrypt('Eden', 'aesEncryptionKey')));\n";
				exe_stat.executeUpdate(add_user);

				add_user = "INSERT INTO User VALUES(3, Hex(aes_encrypt('Lebron', 'aesEncryptionKey')));";
				exe_stat.executeUpdate(add_user);

				/*
				 * Create the Term table inside of the DB so we can populate the rows
				 * Update the term table
				 */
				System.out.println("Creating Term Table...");
				String term_table = "CREATE TABLE db_spatel392.Term (\r\n" + "	term_id int NOT NULL,\r\n" + "	term_name varchar(255) NOT NULL,\r\n" + "	CONSTRAINT pkTerm PRIMARY KEY (term_id)\r\n" + ");";
				exe_stat.executeUpdate(term_table);
				System.out.println("Term Table created successfully...");
				
				/*
				 * Create the Result table inside of the DB so we can populate the rows
				 */
				System.out.println("Creating Result Table...");
				final String RESULT_TABLE =
						"CREATE TABLE IF NOT EXISTS db_spatel392.Result (\n" +
								"  student_id INT NOT NULL,\n" +
								"  course_name VARCHAR(45) NOT NULL,\n" +
								"  letter_grade VARCHAR(3) NOT NULL,\n" +
								"  term_id INT NOT NULL,\n" +
								"  year INT NULL,\n" +
								"  PRIMARY KEY (student_id, course_name, letter_grade, term_id),\n" +
								"  INDEX letter_grade_idx (letter_grade ASC),\n" +
								"  INDEX term_idx (term_id ASC),\n" +
								"  CONSTRAINT student_id\n" +
								"    FOREIGN KEY (student_id)\n" +
								"    REFERENCES db_spatel392.Student (student_id)\n" +
								"    ON DELETE NO ACTION\n" +
								"    ON UPDATE NO ACTION,\n" +
								"  CONSTRAINT letter_grade\n" +
								"    FOREIGN KEY (letter_grade)\n" +
								"    REFERENCES db_spatel392.GPA (letter_grade)\n" +
								"    ON DELETE NO ACTION\n" +
								"    ON UPDATE NO ACTION,\n" +
								"  CONSTRAINT term\n" +
								"    FOREIGN KEY (term_id)\n" +
								"    REFERENCES db_spatel392.Term (term_id)\n" +
								"    ON DELETE NO ACTION\n" +
								"    ON UPDATE NO ACTION)\n" +
								"ENGINE = InnoDB;\n";
				exe_stat.executeUpdate(RESULT_TABLE);
				System.out.println("Result Table created successfully...");
	
			}

			// close the connection to the db
			
		}

		/*
		 * Catch potential exceptions and logs them
		 */
		catch (Exception e) 
		{
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
		}
		return new_session;
	}

	 /**
	   * Attempts to close all database resources including connections, statements etc
	   */
	private void close() 
	{
		try 
		{
			if (data_table != null) 
			{
				data_table.close();
			}

			if (exe_stat != null) 
			{
				exe_stat.close();
			}

			if (new_session != null) 
			{
				new_session.close();
			}
		}
		
		// Catch potential exceptions and logs them
		catch (Exception e) 
		{
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
		}
	}
}