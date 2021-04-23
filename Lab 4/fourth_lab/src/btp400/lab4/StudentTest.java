package btp400.lab4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Student Class
 */
class StudentTest {
    static DatabaseHandler dbHandler;
    static Connection connection;
    static Student student;
    static Student student2;
    static Student student3;
    static Student student4;

    @BeforeEach
    void setUp()  {
//        System.setSecurityManager(new SecurityManager());
        dbHandler = new DatabaseHandler();
        try {
            connection = dbHandler.db();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void login() {
        //attempt login with wrong credentials (should display incorrect password)
        student = new Student(1, "thomas", "Thomas");

        //attempt login with correct credentials (should display successful login)
        student2 = new Student(1, "1", "Sahil");
    }

    @Test
    void add() {
        student = new Student("ACC180", 1);
        assertTrue(student.add("ACC180", Term.Summer, 2021, Grade.A));
    }

    @Test
    void save() {
        student = new Student("ACC180", 1);
        student.add("ACC180", Term.Summer, 2021, Grade.A);
        //assuming an identical result has not been persisted to the database for this student, this test should pass
        student.save();

        //trying to save identical results for the same student will throw an exception
        student.save();
    }

    @Test
    void getGPA() {
        //get GPA for student whose results have been added assuming the student has a grade higher than F
        //Expected to return a value not equal to zero
        student = new Student("ACC180", 1);
        student.add("ACC180", Term.Summer, 2021, Grade.A);
        assertNotEquals(0, student.getGPA());

        student4 = new Student("ACC180", 4);
        //get GPA for student whose results have not been added
        //Expected to return zero
        assertEquals(0, student4.getGPA());
    }

    @Test
    void getClassGPA() {
        student2 = new Student("ACC180", 2);
        student3 = new Student("ACC180", 3);
        Set<Student> students = new LinkedHashSet<>();
        students.add(student2);
        students.add(student3);
        student2.add("ACC180", Term.Summer, 2021, Grade.A);
        student3.add("ACC180", Term.Summer, 2021, Grade.A);

        //Get the class GPA for a set of students
        //assuming the students' grades are not all F's, this method is not expected to return a zero
        assertNotEquals(0, Student.getClassGPA("ACC180", Term.Winter, 2021, students));

        //Get the class GPA for a set of students with all A's
        //Expected to return a 4.0 GPA value
        assertEquals(4.0, Student.getClassGPA("ACC180", Term.Winter, 2021, students));

        //Get the class GPA for an empty set of students
        //Expected to return a zero
        assertEquals(0, Student.getClassGPA("ACC180", Term.Winter, 2021, new LinkedHashSet<>()));

    }

    @Test
    void printResult() {
        student = new Student();
        //print results for student whose results have not been added
        //Expected to print a blank
        student.printResult();

        student.add("ACC180", Term.Summer, 2021, Grade.A);
        student.save();
        //print results for student whose results have been added
        //Not expected to print a blank
        student.printResult();
    }
}